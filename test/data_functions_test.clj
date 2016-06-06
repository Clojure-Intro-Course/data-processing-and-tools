(ns data-functions-test
  (:use expectations)
  (:require [core :refer :all]
            [data_functions :refer :all]
            [data :refer :all]))

(expect 0 (lang? "R0-1"))
(expect 1 (lang? "CM0-2"))


