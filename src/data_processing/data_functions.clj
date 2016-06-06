(ns data_processing.data_functions
  (:use data_processing.data))

(defn vector->map
  [input-vector]
  "converts a vector into a map"
  (apply hash-map input-vector))

(defn time-adj-result
  [person]
  "takes a person as an argument
  and returns the total time in seconds that should be adjusted"
  (reduce
    (fn [cumul each]
      (if-let
        [each-n (:time-adj each)]
        (+ cumul each-n)
        cumul))
    0
    (vals (vector->map person))))

(defn after-time-adj
  [person]
  "takes a person as an argument
  and computes the total time in seconds that the person takes for the test after adjustment"
  (let [last-entry (last person)]
    (+ (* (:min last-entry) 60) (:sec last-entry) (time-adj-result person))))

; top level function
(defn total-time [person]
  "takes a person as an argument
  and returns a nice looking string in the form \"XX minutes XX seconds\""
  (str (quot (after-time-adj person) 60) " minutes " (mod (after-time-adj person) 60) " seconds"))

; top level function
(defn solved-questions [person]
  "takes a person as an argument
  and returns the number of questions the person solved"
  (count (filter (fn [question] (:solved question)) (vals (vector->map person)))))

(defn get-question [person question]
  ((apply hash-map person) question))

(defn get-map [person th]
  (nth person (+ (* th 2) 1)))

(defn get-key [person th]
  (nth person (* th 2)))

(defn time->seconds [h-map]
  (+ (* (:min h-map) 60) (:sec h-map)))

; 1 is Racket 2 is Clojure
(defn language-check [person]
  (if (= (first (get-key person 0)) \R) 1 2))

(defn nice-time [seconds]
  (str (quot seconds 60) "min " (mod seconds 60) "sec"))
; convert the resulting vector into a nice way
(defn nice-str [input-vec]
  (vec (map (fn [each-vec] [(first each-vec) (nice-time (second each-vec)) (last each-vec)]) input-vec)))

(defn processor [person-vec result previous-t]
  (if (empty? person-vec)
    result
    (let [id (first (first person-vec)) h-map (second (first person-vec))]
     (processor (rest person-vec) (conj result [id (- (time->seconds h-map) previous-t) (:solved h-map)]) (time->seconds h-map)))))

(defn take-q-info [person]
  (let [pair-vec (vec (map vec (partition 2 person)))]
    ;(nice-str (processor pair-vec [] 0)) ))
    (processor pair-vec [] 0) ))


; filter normal stuff
; filter repeated questions
; for each repeated question
;    - find the location
;    - update it by assoc-in
(defn lang? [s]
  (if (= (first s) \R) 0 1)) ;racket 0 clojure 1

(defn normal [person]
  (vec (filter (fn [each-vec] (<= (count (first each-vec)) 5)) (take-q-info person))))
(defn abnormal [person]
  (vec (filter (fn [each-vec] (> (count (first each-vec)) 5)) (take-q-info person))))

(defn do-sth [original each-retry info]
  (map
    (fn [each-vec]
      (if (= (subs (first each-vec) 0 (+ info 4)) (subs (first each-retry) 0 (+ info 4)))
        [(first each-vec) (+ (second each-vec) (second each-retry)) (last each-retry)]
        each-vec))
    original))

(defn i-function [original retry]
  (if (empty? retry)
    original
    (i-function (do-sth original (first retry) (lang? (first (first original))) ) (rest retry)))) ; racket 0 clojure 1 TODO: automatic detection of Clojure or Java - Done

(defn adjusted-data [person]
  (let [original (normal person)
        retry (abnormal person)]
    (i-function original retry)))


; ------------------------------------------------------------------------------

; need a function that takes a vector of data points and print all the data

; ugly code for printing
(println "|Name\t\t\t|Total Time\t\t|Solved Questions/Number of Questions")
(println)
(printf "Someone-Racket\t\t%s\t%s\n" (total-time person) (str (solved-questions person) "/" 8))
(println (adjusted-data person))
