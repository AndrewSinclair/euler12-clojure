(ns euler12.core
  (:require [clojure.math.numeric-tower :as math]))

;https://crossclj.info/fun/clojure.contrib.lazy-seqs/primes.html
(def primes
  (concat
    [2 3 5 7]
    (lazy-seq
      (let [primes-from
            (fn primes-from [n [f & r]]
              (if (some #(zero? (rem n %))
                    (take-while #(<= (* % %) n) primes))
                (recur (+ n f) r)
                (lazy-seq (cons n (primes-from (+ n f) r)))))
            wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6  4  2
                          6 4 6 8 4 2 4 2 4 8 6 4 6 2  4  6
                          2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
        (primes-from 11 wheel)))))

(defn int-sqrt [n] (math/floor (math/sqrt n)))

(defn update-powers [num powers]
  (if (contains? powers num)
    (update-in powers [num] inc)
    (assoc powers num 1)))

(defn factorize
  ([n] (factorize n 2 0 {}))
  ([n p i decomposition]
    (let [max-p (int-sqrt n)]
      (if (<= p max-p)
        (if (= 0 (rem n p))
          (recur (quot n p) 2 0 (update-powers p decomposition))
          (recur n (nth primes i) (inc i) decomposition))
        (update-powers n decomposition)))))

(defn count-divisors [decomposition]
  (reduce * 1 (map inc (vals decomposition))))

(defn generate-triangle-nums
  [n i]
  (lazy-seq (cons n (generate-triangle-nums (+ n i) (inc i)))))

(def triangle-nums (generate-triangle-nums 1 2))

(defn num-divisors [n] (->> n factorize count-divisors))

(defn calc-euler-12
  [max-num]
  (first (filter #(< max-num (num-divisors %)) triangle-nums)))

(defn -main
  "Euler12"
  [& args]
  (do (println args)
  (println (calc-euler-12 (first args)))))

