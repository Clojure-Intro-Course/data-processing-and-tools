(ns data-test
  (:use expectations)
  (:require [core :refer :all]
            [data :refer :all]
            [data-functions :refer :all]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.spec.test.alpha :as stest]))

;;solved field should alwyas be a boolean
(s/def ::solved-field boolean?)

;;minute field should always be an integer greater than zero.
(s/def ::min nat-int?)

;;sec field should always be an integer between zero inclusive and 60 exclusive
(s/def ::sec (s/and nat-int?  #(<= 0 % 59)))

;;right field should have the correct keyword
(s/def ::right? #{:all :most :some :never})

;;runs should be a non-negativer integer
(s/def ::runs nat-int?)

;;no-error field should be a boolean
(s/def ::no-error boolean?)

;;time-adj field should be an integer, and not zero
(s/def ::time-adj (s/and #(not (= % 0)) int?))

;;question-number should be in the range of questions, and should begin with CM, CS or R
(s/def ::question-number question-number?)

;;every question-entry should have the correct map
(s/def ::question-entry (s/keys :req-un [::min
                                        ::sec
                                        ::runs
                                        ::solved
                                        ::no-error
                                        ::right?]
                                :opt-un [::time-adj]))

;;each line of a subject should have a question number and an question-entry
(s/def ::question-number-in-subject (s/tuple ::question-number ::question-entry))

;;each subject should be
(defn subject?
  [inp-subject]
  (let [pairs (mapv vec (partition 2 (first (rest inp-subject))))
        validator #(s/valid? ::question-number-in-subject %)]
  (and
    (every? identity (map validator pairs))
    (s/valid? valid-subj-numbers (first inp-subject)))))

(s/def ::subject subject?)

(s/def ::subject-data (s/coll-of ::subject))




;;=====================Spec for result tree

(s/def ::tries nat-int?)
(s/def ::successes nat-int?)
(s/def ::failures nat-int?)
(s/def ::total-time nat-int?)
(s/def ::average-time (s/and #(<= 0 % ) number?))


;; a result should have the correct information
 (s/def ::result (s/and (s/keys :req-un [::tries ::successes ::failures ::total-time ::average-time])
                        #(= (:tries %) (+ (:successes %) (:failures %)))
                        #(= (:average-time %) (/ (:total-time %) (:tries %)))))

(s/def ::R ::result)
(s/def ::CS ::result)
(s/def ::CM ::result)
(s/def ::lang-keyword #(contains? #{:R :CS :CM} %))

;;a question result should have three result fields
(s/def ::question-result (s/keys :req-un [::R ::CS ::CM]))

;;a result tree should have only question-results
(s/def ::result-tree (s/map-of ::question-number ::question-result))

(def example (build-result-tree subjects))
