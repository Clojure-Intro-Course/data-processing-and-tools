(ns data)

(def R2
  ["R0-1" {:solved true, :min 1, :sec 1, :right? :all, :runs 2, :no-error false}
   "R0-3" {:solved true, :min 2, :sec 30, :right? :all, :runs 3, :no-error false}
   "R1-1" {:solved true, :min 3, :sec 9, :right? :all, :runs 2, :no-error false}
   "R1-4" {:solved false, :min 6, :sec 0, :right? :never, :runs 3, :no-error false}
   "R2-1" {:solved true, :min 9, :sec 32, :right? :all, :runs 3, :no-error false}
   "R2-4" {:solved true, :min 13, :sec 58, :right? :all, :runs 4, :no-error false}
   "R3-3" {:solved true, :min 19, :sec 52, :right? :all, :runs 3, :no-error false}
   "R3-4" {:solved true, :min 20, :sec 56, :right? :all, :runs 2, :no-error false}])

(def CM2
  ["CM0-2" {:solved true, :min 1, :sec 37, :right? :all, :runs 2, :no-error false}
   "CM0-4" {:solved false, :min 4, :sec 40, :right? :never, :runs 2, :no-error false}
   "CM1-2" {:solved true, :min 6, :sec 1, :right? :all, :runs 2, :no-error false}
   "CM1-3" {:solved true, :min 6, :sec 54, :right? :all, :runs 2, :no-error false}
   "CM2-2" {:solved true, :min 7, :sec 37, :right? :all, :runs 2, :no-error false}
   "CM2-3" {:solved true, :min 8, :sec 26, :right? :all, :runs 2, :no-error false}
   "CM3-1" {:solved true, :min 11, :sec 16, :right? :all, :runs 3, :no-error false}
   "CM3-2" {:solved true, :min 13, :sec 51, :right? :all, :runs 3, :no-error false}
   "CM0-4-re-1" {:solved true, :min 16, :sec 22, :right? :all, :runs 3, :no-error false}])

(def R6
  ["R0-2" {:solved true, :min 0, :sec 59, :right? :all, :runs 2, :no-error false}
   "R0-4" {:solved true, :min 1, :sec 35, :right? :all, :runs 2, :no-error false}
   "R1-1" {:solved false, :min 3, :sec 28, :right? :some, :runs 1, :no-error false}
   "R1-4" {:solved true, :min 6, :sec 7, :right? :most, :runs 2, :no-error false}
   "R1-1-re-1" {:solved false, :min 7, :sec 38, :right? :some, :runs 3, :no-error false}
   "R2-3" {:solved true, :min 10, :sec 9, :right? :most, :runs 5, :no-error false}
   "R1-1-re-2" {:solved true, :min 10, :sec 59, :right? :all, :runs 1, :no-error true}
   "R2-4" {:solved true, :min 12, :sec 13, :right? :all, :runs 2, :no-error false}
   "R3-2" {:solved true, :min 13, :sec 26, :right? :all, :runs 2, :no-error false}
   "R3-4" {:solved true, :min 14, :sec 25, :right? :all, :runs 2, :no-error false}])

(def CM6
  ["CM0-1" {:solved true, :min 1, :sec 22, :right? :all, :runs 2, :no-error false}
   "CM0-3" {:solved true, :min 3, :sec 0, :right? :all, :runs 2, :no-error false}
   "CM1-2" {:solved true, :min 6, :sec 13, :right? :most, :runs 2, :no-error false}
   "CM1-3" {:solved true, :min 8, :sec 58, :right? :most, :runs 3, :no-error false}
   "CM2-1" {:solved false, :min 21, :sec 0, :right? :most, :runs 6, :no-error false}])


(def R13
  ["R0-1" {:solved true, :min 0, :sec 23, :right? :all, :runs 2, :no-error false}
   "R0-2" {:solved true, :min 1, :sec 6, :right? :all, :runs 2, :no-error false}
   "R1-3" {:solved true, :min 2, :sec 55, :right? :all, :runs 3, :no-error false}
   "R1-4" {:solved true, :min 4, :sec 9, :right? :all, :runs 2, :no-error false}
   "R2-1" {:solved true, :min 5, :sec 50, :right? :all, :runs 3, :no-error false}
   "R2-3" {:solved true, :min 6, :sec 37, :right? :all, :runs 2, :no-error false}
   "R3-2" {:solved true, :min 7, :sec 21, :right? :all, :runs 2, :no-error false}
   "R3-4" {:solved true, :min 8, :sec 32, :right? :all, :runs 3, :no-error false, :time-adj -39}])

(def CM13
  ["CM0-3" {:solved true, :min 2, :sec 0, :right? :all, :runs 2, :no-error false}
   "CM0-4" {:solved true, :min 3, :sec 6, :right? :all, :runs 2, :no-error false}
   "CM1-1" {:solved true, :min 5, :sec 9, :right? :all, :runs 2, :no-error false}
   "CM1-2" {:solved true, :min 7, :sec 11, :right? :all, :runs 2, :no-error false}
   "CM2-2" {:solved true, :min 8, :sec 33, :right? :all, :runs 2, :no-error false}
   "CM2-4" {:solved true, :min 10, :sec 24, :right? :all, :runs 3, :no-error false}
   "CM3-1" {:solved false, :min 17, :sec 11, :right? :all, :runs 5, :no-error false}
   "CM3-3" {:solved true, :min 19, :sec 38, :right? :all, :runs 2, :no-error false}
   "CM3-1-re-1" {:solved false, :min 21, :sec 0, :right? :most, :runs 1, :no-error false}]) ; solved should be false in the processing of the data bc the time exceeds 21 mins



(def R16
  ["R0-3" {:solved true, :min 0, :sec 42, :right? :all, :runs 1, :no-error true}
   "R0-4" {:solved true, :min 4, :sec 20, :right? :all, :runs 4, :no-error false}
   "R1-2" {:solved true, :min 5, :sec 34, :right? :all, :runs 2, :no-error false}
   "R1-3" {:solved true, :min 8, :sec 26, :right? :all, :runs 2, :no-error false}
   "R2-2" {:solved true, :min 8, :sec 57, :right? :all, :runs 2, :no-error false}
   "R2-4" {:solved true, :min 10, :sec 23, :right? :all, :runs 2, :no-error false}
   "R3-1" {:solved true, :min 13, :sec 38, :right? :all, :runs 2, :no-error false}
   "R3-2" {:solved true, :min 14, :sec 41, :right? :all, :runs 2, :no-error false}]) ; There was an error in one of the tests for R3-2, but we need to drop this anyway

(def CM16
  ["CM0-1" {:solved true, :min 1, :sec 26, :right? :all, :runs 2, :no-error false, :time-adj -25}
   "CM0-2" {:solved true, :min 2, :sec 33, :right? :all, :runs 2, :no-error false}
   "CM1-1" {:solved true, :min 4, :sec 14, :right? :all, :runs 2, :no-error false}
   "CM1-4" {:solved true, :min 6, :sec 51, :right? :all, :runs 2, :no-error false}
   "CM2-1" {:solved true, :min 13, :sec 35, :right? :all, :runs 3, :no-error false}
   "CM2-3" {:solved true, :min 14, :sec 36, :right? :all, :runs 2, :no-error false}
   "CM3-3" {:solved true, :min 15, :sec 4, :right? :all, :runs 1, :no-error true} ; There was no error in the code
   "CM3-4" {:solved true, :min 17, :sec 0, :right? :all, :runs 2, :no-error false, :time-adj -42}])

(def R17
  ["R0-1" {:solved true, :min 0, :sec 40, :right? :all, :runs 2, :no-error false}
   "R0-2" {:solved true, :min 1, :sec 23, :right? :all, :runs 2, :no-error false}
   "R1-2" {:solved true, :min 3, :sec 40, :right? :most, :runs 4, :no-error false}
   "R1-4" {:solved true, :min 5, :sec 4, :right? :all, :runs 2, :no-error false}
   "R2-2" {:solved true, :min 5, :sec 39, :right? :all, :runs 2, :no-error false}
   "R2-4" {:solved true, :min 8, :sec 3, :right? :all, :runs 4, :no-error false}
   "R3-1" {:solved true, :min 11, :sec 35, :right? :all, :runs 2, :no-error false}
   "R3-3" {:solved true, :min 19, :sec 58, :right? :all, :runs 3, :no-error false}])

(def CS17
  ["CS0-3" {:solved true, :min 3, :sec 43, :right? :all, :runs 3, :no-error false}
   "CS0-4" {:solved true, :min 8, :sec 11, :right? :all, :runs 3, :no-error false}
   "CS1-1" {:solved true, :min 9, :sec 30, :right? :most, :runs 2, :no-error false}
   "CS1-3" {:solved true, :min 12, :sec 58, :right? :all, :runs 3, :no-error false}
   "CS2-1" {:solved true, :min 15, :sec 31, :right? :all, :runs 2, :no-error false}
   "CS2-3" {:solved true, :min 16, :sec 30, :right? :all, :runs 1, :no-error true}
   "CS3-2" {:solved true, :min 19, :sec 45, :right? :all, :runs 3, :no-error false}
   "CS3-4" {:solved false, :min 21, :sec 0, :right? :all, :runs 1, :no-error false}])

(def R20
  ["R0-3" {:solved true, :min 0, :sec 48, :right? :all, :runs 2, :no-error false}
   "R0-4" {:solved true, :min 8, :sec 38, :right? :all, :runs 4, :no-error false}
   "R1-1" {:solved true, :min 10, :sec 51, :right? :all, :runs 3, :no-error false}
   "R1-2" {:solved true, :min 12, :sec 33, :right? :all, :runs 3, :no-error false}
   "R2-2" {:solved true, :min 13, :sec 24, :right? :all, :runs 2, :no-error false}
   "R2-4" {:solved true, :min 18, :sec 27, :right? :all, :runs 2, :no-error false}
   "R3-2" {:solved false, :min 21, :sec 0, :right? :some, :runs 2, :no-error false}])

(def CS20
  ["CS0-1" {:solved true, :min 0, :sec 50, :right? :all, :runs 2, :no-error false}
   "CS0-4" {:solved true, :min 2, :sec 12, :right? :all, :runs 1, :no-error true}
   "CS1-2" {:solved true, :min 3, :sec 07, :right? :all, :runs 1, :no-error false}
   "CS1-3" {:solved true, :min 13, :sec 37, :right? :all, :runs 5, :no-error false} ;input wrong command in terminal. Returned all true but didnt't run correctly
   "CS2-2" {:solved false, :min 21, :sec 0, :right? :never, :runs 5, :no-error false}])

(def R23
  ["R0-3" {:solved true, :min 1, :sec 45, :right? :most, :runs 3, :no-error false}
   "R0-4" {:solved true, :min 3, :sec 3, :right? :most, :runs 3, :no-error false}
   "R1-1" {:solved true, :min 4, :sec 23, :right? :all, :runs 2, :no-error false}
   "R1-4" {:solved true, :min 6, :sec 27, :right? :all, :runs 2, :no-error false}
   "R2-1" {:solved false, :min 9, :sec 20, :right? :some, :runs 4, :no-error false}
   "R2-3" {:solved true, :min 10, :sec 55, :right? :all, :runs 2, :no-error false}
   "R3-2" {:solved true, :min 12, :sec 54, :right? :all, :runs 2, :no-error false}
   "R3-3" {:solved false, :min 15, :sec 25, :right? :some, :runs 2, :no-error false}
   "R2-1-re-1" {:solved true, :min 18, :sec 22, :right? :all, :runs 6, :no-error false}
   "R3-3-re-1" {:solved false, :min 21, :sec 0, :right? :some, :runs 7, :no-error false}])

(def CS23
  ["CS0-1" {:solved true, :min 2, :sec 1, :right? :all, :runs 2, :no-error false}
   "CS0-2" {:solved true, :min 3, :sec 7, :right? :all, :runs 2, :no-error false}
   "CS1-2" {:solved true, :min 4, :sec 37, :right? :all, :runs 2, :no-error false}
   "CS1-3" {:solved false, :min 7, :sec 16, :right? :some, :runs 2, :no-error false}
   "CS2-2" {:solved true, :min 9, :sec 42, :right? :most, :runs 2, :no-error false}
   "CS2-4" {:solved true, :min 11, :sec 48, :right? :all, :runs 3, :no-error false}
   "CS3-1" {:solved false, :min 13, :sec 41, :right? :some, :runs 1, :no-error false}
   "CS3-4" {:solved true, :min 15, :sec 2, :right? :all, :runs 2, :no-error false}
   "CS1-3-re-1" {:solved false, :min 17, :sec 37, :right? :some, :runs 1, :no-error false}
   "CS3-1-re-1" {:solved false, :min 21, :sec 0, :right? :some, :runs 1, :no-error false}])


(def R25
  ["R0-1" {:solved true, :min 1, :sec 10, :right? :all, :runs 2, :no-error false}
   "R0-3" {:solved true, :min 2, :sec 5, :right? :all, :runs 2, :no-error false}
   "R1-3" {:solved true, :min 7, :sec 5, :right? :all, :runs 4, :no-error false}
   "R1-4" {:solved true, :min 9, :sec 36, :right? :all, :runs 2, :no-error false}
   "R2-2" {:solved true, :min 10, :sec 43, :right? :all, :runs 2, :no-error false}
   "R2-3" {:solved true, :min 11, :sec 48, :right? :all, :runs 2, :no-error false}
   "R3-1" {:solved true, :min 14, :sec 4, :right? :all, :runs 2, :no-error false}
   "R3-3" {:solved true, :min 16, :sec 46, :right? :all, :runs 2, :no-error false}])

(def CM25
  ["CM0-2" {:solved true, :min 1, :sec 20, :right? :all, :runs 2, :no-error false}
   "CM0-4" {:solved true, :min 2, :sec 37, :right? :all, :runs 2, :no-error false}
   "CM1-1" {:solved true, :min 4, :sec 3, :right? :all, :runs 2, :no-error false}
   "CM1-2" {:solved true, :min 5, :sec 47, :right? :all, :runs 2, :no-error false}
   "CM2-1" {:solved true, :min 7, :sec 37, :right? :all, :runs 2, :no-error false}
   "CM2-4" {:solved true, :min 9, :sec 12, :right? :all, :runs 2, :no-error false}
   "CM3-2" {:solved true, :min 12, :sec 7, :right? :all, :runs 3, :no-error false}
   "CM3-4" {:solved true, :min 13, :sec 9, :right? :all, :runs 2, :no-error false}])


(def R29
  ["R0-1" {:solved true, :min 1, :sec 3, :right? :all, :runs 2, :no-error false}
   "R0-4" {:solved true, :min 2, :sec 5, :right? :all, :runs 2, :no-error false}
   "R1-1" {:solved true, :min 2, :sec 54, :right? :all, :runs 1, :no-error true}
   "R1-2" {:solved false, :min 7, :sec 42, :right? :some, :runs 6, :no-error false}
   "R2-2" {:solved true, :min 8, :sec 55, :right? :most, :runs 2, :no-error false}
   "R2-3" {:solved false, :min 13, :sec 22, :right? :some, :runs 7, :no-error false}
   "R3-1" {:solved false, :min 19, :sec 58, :right? :never, :runs 4, :no-error false}
   "R3-4" {:solved false, :min 21, :sec 0, :right? :all, :runs 1, :no-error false}])

(def CM29
  ["CM0-2" {:solved true, :min 1, :sec 49, :right? :all, :runs 2, :no-error false}
   "CM0-3" {:solved true, :min 4, :sec 40, :right? :all, :runs 1, :no-error true}
   "CM1-3" {:solved false, :min 7, :sec 55, :right? :most, :runs 1, :no-error true}
   ;On this question (CM1-3) once the error message didn't come up but the tests were still
   ;failing the participant moved on without solving the problem
   "CM1-4" {:solved false, :min 11, :sec 34, :right? :all, :runs 1, :no-error false}
   "CM2-1" {:solved false, :min 15, :sec 23, :right? :never, :runs 1, :no-error false}
   "CM2-4" {:solved false, :min 21, :sec 0, :right? :some, :runs 4, :no-error false}])

(def qTables [R2 CM2 R6 CM6 R13 CM13 R16 CM16 R17 CS17 R20 CS20 R23 CS23 R25 CM25 R29 CM29])

(def qList ["0-1" "0-2" "0-3" "0-4" "1-1" "1-2" "1-3" "1-4" "2-1" "2-2" "2-3" "2-4" "3-1" "3-2" "3-3" "3-4"])
