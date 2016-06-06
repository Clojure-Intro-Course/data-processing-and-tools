(defproject data-processing "0.1.0-SNAPSHOT"
  :description "Data processing project for Clojure error message usability study"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [expectations "2.1.8"]]
  :main ^:skip-aot data_processing.core
  :plugins [[lein-expectations "0.0.8"]])
