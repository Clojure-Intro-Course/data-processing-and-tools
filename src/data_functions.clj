(ns data_functions
  (:use data))


;; generic helper functions =============================================================

(defn get-map
  "gets the map of nth vector"
  [person th]
  (nth person (+ (* th 2) 1)))

(defn get-key
  "gets the key (string) of nth vector"
  [person th]
  (nth person (* th 2)))

(defn time->seconds
  "takes the map of a question in a person structure
  and returns the converted time in seconds"
  [h-map]
  (+ (* (:min h-map) 60) (:sec h-map)))

(defn vector->map
  "converts a vector into a map"
  [input-vector]
  (apply hash-map input-vector))

(defn nice-time
  "takes seconds and converts it into a nice looking string \"XX minutes XX seconds\""
  [seconds]
  (str (quot seconds 60) "min " (mod seconds 60) "sec"))

(defn lang?
  "takes a string
  and returns 0 if it represents racket 1 if it represents clojure"
  [s]
  (if (= (first s) \R) 0 1))


;; time adjustment functions =============================================================

(defn time-adj-result
  "takes a person as an argument
  and returns the total time in seconds that should be adjusted"
  [person]
  (reduce
    (fn [cumul each]
      (if-let
        [each-n (:time-adj each)]
        (+ cumul each-n)
        cumul))
    0
    (vals (vector->map person))))

(defn after-time-adj
  "takes a person as an argument
  and computes the total time in seconds that the person takes for the test after adjustment"
  [person]
  (let [last-entry (last person)]
    (+ (time->seconds last-entry) (time-adj-result person))))


;; # of solved problem calculation =============================================================

(defn solved-questions
  "takes a person as an argument
  and returns the number of questions the person solved"
  [person]
  (count (filter (fn [question] (:solved question)) (vals (vector->map person)))))


;; extracting each question info functions #0 ==========================================================

(defn nice-str
  "for each vector inside the processed vector,
  convert the time into a nice-looking string"
  [input-vec]
  (vec (map (fn [each-vec] [(first each-vec) (nice-time (second each-vec)) (last each-vec)]) input-vec)))

(defn nice-solved
  "for each vector inside the processed vector,
  convert the boolean into a :solved or :unsolved keyword"
  [input-vec]
  (map  #(assoc % 2 (if (get % 2) :solved :unsolved)) input-vec))

(defn processor
  "takes the nested vector structure of a person structure, an accumulated resulting vector, and the previous time,
  and returns the accumulated resulting vector when the nested vector structures becomes empty"
  [person-vec result previous-t]
  (if (empty? person-vec)
    result
    (let [id (first (first person-vec))
          h-map (second (first person-vec))]
     (processor (rest person-vec)
                (conj result [id (- (time->seconds h-map) previous-t) (:solved h-map)])
                (time->seconds h-map)))))

(defn take-q-info
  "takes a person structure and returns the vector of
  questions' info that the person attemped to solve
  (re-attempts are separately calculated)"
  [person]
  (let [pair-vec (vec (map vec (partition 2 person)))]
    (processor pair-vec [] 0) ))

;; extracting each question info functions #1 (filtering and merging retries) ===========================

(defn retry-filter
  "takes a person structure and a boolean that indicates if retry data is wanted,
  returns retry or initial try data according to the boolean value"
  [person decide]
  (vec
    (filter
      (fn [each-vec] ((if decide > <=) (count (first each-vec)) 5))
      (take-q-info person))))

(defn merge-retry
  "merges a retry into the corresponding initial try"
  [original each-retry info]
  (map
    (fn [each-vec]
      (if (= (subs (first each-vec) 0 (+ info 4)) (subs (first each-retry) 0 (+ info 4)))
        [(first each-vec) (+ (second each-vec) (second each-retry)) (last each-retry)]
        each-vec))
    original))

(defn merge-recur
  "takes two vectors, the initial tries and the retries,
  and returns the merged resulting vector"
  [original retry]
  (if (empty? retry)
    original
    (merge-recur (merge-retry original (first retry) (lang? (first (first original))) )
                 (rest retry))))

(defn adjusted-data
  "takes a person structure and returns the vector of
  questions' info that the person attemped to solve
  (re-attempts are included in the intial tries)"
  [person]
  (let [original (retry-filter person false)
        retry (retry-filter person true)]
    (merge-recur original retry)))


;; question by question analysis =======================================================

; find the avg time of the vector
(defn find-avg-t [v]
  (int (/ (reduce (fn [default each] (+ default (second each))) 0 v) (count v))))

(defn correct-ans [v]
  (if (= (count v) 0)
    0
    (* 100 (double (/ (count (filter #(last %) v)) (count v))))))

; a funtion that takes a person,
; and returns the specific question
(defn get-question-from-person [person question]
  (let [lang (lang? (first person))]
    (filter #(= (subs (first %) (+ lang 1) (+ lang 4)) question) (adjusted-data person))))

; takes a question and returns a vector of every try of that question
(defn get-question [question]
  (reduce (fn [default each] (concat default (get-question-from-person each question))) [] q-tables))

; takes a question and version info, returns the targeted result
(defn get-question-info [question ver]
  (let [tar (str ver question)]
    (filter #(= tar (first %)) (get-question question))))


;; printing result =====================================================================

(defn print-result [p-vec names]
  (when ((complement empty?) p-vec)
    (let [head (first p-vec)]
      (printf "%s\t\t%s\t%s\n"
              (first names)
              (nice-time (after-time-adj head))
              (str (solved-questions head) "/" 8))
      (print "Tried Q: ")
      (println (nice-str (adjusted-data head)))
      (println)
      (print-result (rest p-vec) (rest names)))))

(defn print-outline
  "takes a vector of data points and prints the data as a table"
  [p-vec names]
  (println "\n|Name\t\t|Total Time\t|Solved Questions/Number of Questions\n")
  (print-result p-vec names)
  (println "===================================END========================================"))

(defn print-all []
  (print-outline q-tables q-names))

(defn print-select [s]
  (let [name-r (str "R" s)
        id-r (.indexOf q-names name-r)
        id-c (inc id-r)
        name-c (get q-names id-c)]
    (print-outline [(get q-tables id-r) (get q-tables id-c)] [name-r name-c])))


(defn print-question [question]
  (let [q-r (get-question-info question "R")
        q-cm (get-question-info question "CM")
        q-cs(get-question-info question "CS")]
    (println "\n========== Question" question " information: ============\n")

    (println "Racket tries:           " (nice-solved (nice-str q-r)))
    (print "\t| Avg time: " (nice-time (find-avg-t q-r)) "|")
    (println " Correct ans: " (correct-ans q-r) "% |")

    (println "Clojure Modified tries: " (nice-solved (nice-str q-cm)))
    (print "\t| Avg time: " (nice-time (find-avg-t q-cm)) "|")
    (println " Correct ans: " (correct-ans q-cm) "% |")

    (println "Clojure Standard tries: " (nice-solved (nice-str q-cs)))
    (print "\t| Avg time: " (nice-time (find-avg-t q-cs)) "|")
    (println " Correct ans: " (correct-ans q-cs) "% |")))

(defn print-all-questions []
  (loop [q-l q-str]
    (if (empty? q-l)
      nil
      (do
        (print-question (first q-l))
        (recur (rest q-l))))))
