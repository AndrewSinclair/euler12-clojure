(defproject euler12 "0.1.0-SNAPSHOT"
  :description "Project Euler 12"
  :url "http://wpgdotnet.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]]
  :main ^:skip-aot euler12.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
