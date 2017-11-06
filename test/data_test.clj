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

(s/def ::question-number-in-subject (s/tuple ::question-number ::question-entry))

(defn subject?
  [inp-subject]
  (let [pairs (partition 2 inp-subject)]
  (reduce #(and (s/valid? ::question-number-in-subject %1) %2) pairs)))

(s/def ::subject-entry ())


(def example 10)
