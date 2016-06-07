(ns data-functions-test
  (:use expectations)
  (:require [core :refer :all]
            [data_functions :refer :all]
            [data :refer :all]))

;; Dummy data for testing ===============================================================

(defn time-d
  [min1 sec1 min2 sec2]
  (- (+ (* min2 60) sec2) (+ (* min1 60) sec1)))

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

(def dummy-R-expected
  [["R0-2" 59 true]
   ["R0-4" (time-d 0 59 1 35) true]
   ["R1-1" (time-d 1 35 3 28) false]
   ["R1-4" (time-d 3 28 6 7) true]
   ["R1-1-re-1" (time-d 6 7 7 38) false]
   ["R2-3" (time-d 7 38 10 9) true]
   ["R1-1-re-2" (time-d 10 9 10 59) true]
   ["R2-4" (time-d 10 59 12 13) true]
   ["R3-2" (time-d 12 13 13 26) false]
   ["R3-4" (time-d 13 26 14 25) true]
   ["R3-2-re-1" (time-d 14 25 21 0) true]])

(def dummy-C
  ["CM0-1" {:solved true, :min 1, :sec 22, :right? :all, :runs 2, :no-error false}
   "CM0-3" {:solved true, :min 3, :sec 0, :right? :all, :runs 2, :no-error false}
   "CM1-2" {:solved true, :min 6, :sec 13, :right? :most, :runs 2, :no-error false, :time-adj -33}
   "CM1-3" {:solved true, :min 8, :sec 58, :right? :most, :runs 3, :no-error false}
   "CM2-1" {:solved false, :min 10, :sec 1, :right? :most, :runs 6, :no-error false}
   "CM2-2" {:solved true, :min 16, :sec 0, :right? :all, :runs 3, :no-error false}
   "CM3-3" {:solved true, :min 17, :sec 29, :right? :all, :runs 2, :no-error false}
   "CM2-1-re-1" {:solved false, :min 21, :sec 0, :right? :most, :runs 4, :no-error false, :time-adj -20}])

(def dummy-C-expected
  [["CM0-1" 82 true]
   ["CM0-3" (time-d 1 22 3 0) true]
   ["CM1-2" (time-d 3 0 6 13) true]
   ["CM1-3" (time-d 6 13 8 58) true]
   ["CM2-1" (time-d 8 58 10 1) false]
   ["CM2-2" (time-d 10 1 16 0) true]
   ["CM3-3" (time-d 16 0 17 29) true]
   ["CM2-1-re-1" (time-d 17 29 21 0) false]])


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

(expect 8 (solved-questions dummy-R))
(expect 6 (solved-questions dummy-C))


;; tests for extracting each question info functions ==========================================================

(expect (take 4 dummy-R-expected)
        (processor (vec (map vec (take 4 (partition 2 dummy-R)))) [] 0))
(expect (take 7 dummy-C-expected)
        (processor (vec (map vec (take 7 (partition 2 dummy-C)))) [] 0))

(expect dummy-R-expected (take-q-info dummy-R))
(expect dummy-C-expected (take-q-info dummy-C))

(expect (filter #(> (count (first %)) 4) dummy-R-expected) (retry-filter dummy-R true))
(expect (filter #(<= (count (first %)) 5) dummy-C-expected) (retry-filter dummy-C false))

(expect [[(get-in dummy-R-expected [2 0])
          (+ (get-in dummy-R-expected [2 1]) (get-in dummy-R-expected [4 1]))
          (get-in dummy-R-expected [4 2])]]
        (vec (merge-retry [(get dummy-R-expected 2)] (get dummy-R-expected 4) 0)))

(expect
  (assoc-in
    (vec (drop-last dummy-C-expected))
    [4 1]
    (+ (get-in dummy-C-expected [4 1]) (get-in dummy-C-expected [7 1])))
  (merge-recur (vec (drop-last dummy-C-expected)) [(last dummy-C-expected)]))

(expect
  (assoc-in
    (vec (drop-last dummy-C-expected))
    [4 1]
    (+ (get-in dummy-C-expected [4 1]) (get-in dummy-C-expected [7 1])))
  (adjusted-data dummy-C))
