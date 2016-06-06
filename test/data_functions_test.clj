(ns data-functions-test
  (:use expectations)
  (:require [core :refer :all]
            [data_functions :refer :all]
            [data :refer :all]))

;; Dummy data for testing ===============================================================

(def dummy-R
  ["R0-2" {:solved true, :min 0, :sec 59, :right? :all, :runs 2, :no-error false}
   "R0-4" {:solved true, :min 1, :sec 35, :right? :all, :runs 2, :no-error false}
   "R1-1" {:solved false, :min 3, :sec 28, :right? :some, :runs 1, :no-error false, :time-adj -7}
   "R1-4" {:solved true, :min 6, :sec 7, :right? :most, :runs 2, :no-error false}
   "R1-1-re-1" {:solved false, :min 7, :sec 38, :right? :some, :runs 3, :no-error false}
   "R2-3" {:solved true, :min 10, :sec 9, :right? :most, :runs 5, :no-error false}
   "R1-1-re-2" {:solved true, :min 10, :sec 59, :right? :all, :runs 1, :no-error true}
   "R2-4" {:solved true, :min 12, :sec 13, :right? :all, :runs 2, :no-error false}
   "R3-2" {:solved false, :min 13, :sec 26, :right? :some, :runs 2, :no-error false}
   "R3-4" {:solved true, :min 14, :sec 25, :right? :all, :runs 2, :no-error false}
   "R3-2-re-1" {:solved true, :min 21, :sec 0, :right? :some, :runs 4, :no-error false, :time-adj 50}])

(def dummy-C
  ["CM0-1" {:solved true, :min 1, :sec 22, :right? :all, :runs 2, :no-error false}
   "CM0-3" {:solved true, :min 3, :sec 0, :right? :all, :runs 2, :no-error false}
   "CM1-2" {:solved true, :min 6, :sec 13, :right? :most, :runs 2, :no-error false, :time-adj -33}
   "CM1-3" {:solved true, :min 8, :sec 58, :right? :most, :runs 3, :no-error false}
   "CM2-1" {:solved false, :min 10, :sec 1, :right? :most, :runs 6, :no-error false}
   "CM2-2" {:solved true, :min 16, :sec 0, :right? :all, :runs 3, :no-error false}
   "CM3-3" {:solved true, :min 17, :sec 29, :right? :all, :runs 2, :no-error false}
   "CM2-1-re-1" {:solved false, :min 21, :sec 0, :right? :most, :runs 4, :no-error false, :time-adj -20}])


;; tests for generic helper functions =======================================================

(expect (dummy-C 1) (get-map dummy-C 0))
(expect "R1-1-re-1" (get-key dummy-R 4))

(expect 180 (time->seconds (dummy-C 3)))
(expect 95 (time->seconds (dummy-R 3)))

(expect 0 (lang? "R0-1"))
(expect 1 (lang? "CM0-2"))


;; tests for time-adj-result =================================================================

(expect 43 (time-adj-result dummy-R))
(expect -53 (time-adj-result dummy-C))

(expect 1303 (after-time-adj dummy-R))
(expect 1207 (after-time-adj dummy-C))

;; tests for # of solved problems =============================================================

;; (defn solved-questions
;;   "takes a person as an argument
;;   and returns the number of questions the person solved"
;;   [person]
;;   (count (filter (fn [question] (:solved question)) (vals (vector->map person)))))
(expect 8 (solved-questions dummy-R))
(expect 6 (solved-questions dummy-C))
