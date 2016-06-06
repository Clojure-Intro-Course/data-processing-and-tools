(ns intro.text
  (:require [corefns.corefns :refer :all]
            [corefns.collection_fns :refer :all]))

; Hashmap indexed by questions
;   R  - Racket
;   CM - clojure modified
;   CS - clojure standard

; tags
;  :solved: represent if the participant solved the problem or not
;       | a boolean value (true, false)
;  :min: represent the minuate when the participant solved the problem
;       | an integer value
;  :sec: represent the second when the participant solved the problem
;       | an integer value
;  :right?: represent the percentage of time thPersistentArrayMapat the participant approached the problem in the right way
;       | a symbol (:all, :most, :some, :never)
;  :runs: represent the number of runs (all runs, even after tests pass)
;       | an integer value
;  :no-error: represent if the participant saw the message or not
;       | a boolean value (true, false)
;  :time-adj: represent the amount of time we need to adjust in case of unexpected situations (optional)
;       | an integer value

; total time, total number of questions, the number of questions a person solved, R/CM/CS, -> one set of comparions
; pull the data from each question (question by question basis) -> the total time for this question, performance according to the type of error message


(def not-nil? (complement nil?))

(defn sortByQType [q]

  (concat

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "0-1")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "0-2")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "0-3")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "0-4")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "1-1")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "1-2")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "1-3")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "1-4")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "2-1")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "2-2")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "2-3")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "2-4")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "3-1")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "3-2")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "3-3")))
                 qTables))

    (filter not-nil?
            (map (fn [x] ((apply assoc {} x)
                          (str q "3-4")))
                 qTables))))

(defn sortAllQData [q]

  (concat

    (sortByQType q)

    (filter not-nil?
            (map
              (fn [x] ((apply assoc {} x)
                       (str "R" q)))
              qTables))

    (filter not-nil?
            (map
              (fn [x] ((apply assoc {} x)
                       (str "CM" q)))
              qTables))

    (filter not-nil?
            (map
              (fn [x] ((apply assoc {} x)
                       (str "CS" q)))
              qTables))))

(defn compareAllQData [q]

  (println

    (count (filter (fn [x] (:solved x))
                   (sortAllQData q)))

    "of"

    (/ (count qTables) 2)

    "solved question" q)

  (println "The average time for question(s)" q "was"
           (int (Math/floor (/ (Math/round (float (/ (+ (* 60 (reduce + (map :min (sortAllQData q))))
                                                        (reduce + (map :sec (sortAllQData q))))
                                                     (count (sortAllQData q)))))
                               60)))

           "minutes and"

           (Math/round (float (mod 60 (/ (+ (* 60 (reduce + (map :min (sortAllQData q))))
                                            (reduce + (map :sec (sortAllQData q))))
                                         (count (sortAllQData q))))))

           "seconds")

  (println
    (count (filter (fn [x] (= :all (:right? x)))
                   (sortAllQData q)))
    "subjects spent all of the time approaching problem(s)" q "correctly")

  (println
    (count (filter (fn [x] (= :most (:right? x)))
                   (sortAllQData q)))
    "subjects spent most of the time approaching problem(s)" q "correctly")

  (println
    (count (filter (fn [x] (= :some (:right? x)))
                   (sortAllQData q)))
    "subjects spent some of the time approaching problem(s)" q "correctly")
  (println
    (count (filter (fn [x] (= :never (:right? x)))
                   (sortAllQData q)))
    "subjects spent none of the time approaching problem(s)" q "correctly")

  (println "The average number of runs for question(s)" q "was"

           (int (Math/round (float (/ (reduce + (map :runs (sortAllQData q)))
                                      (count (sortAllQData q)))))))

  (println

    (count (filter (fn [x] (not (:no-error x)))
                   (sortAllQData q)))

    "of"

    (/ (count qTables) 2)

    "viewed the error message for question(s)" q)

  )


;*************************************************************************************************

; compareAllQData takes a specific question in string form (ie "0-1") and prints data about that question.
; it also takes a question type (ie "R" "CM" or "CS") and prints data about those questions, although that feature isn't entirely finished
(compareAllQData "0-1")





