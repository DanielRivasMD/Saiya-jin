;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.rctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "rctl.edn")

(def hc-change-picker ["changed_file_picker"])
(def mc-open-file ["OpenFile"])
(def hc-file-picker ["file_picker"])
(def mc-new-open-file ["AddTab,OpenFile"])
(def hc-buffer-picker ["buffer_picker"])
(def mc-cmd ["CommandMode"])

(defn rctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]} [(c/mk c/bwp c/al)      [(c/mk c/bw c/al)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/ar)      [(c/mk c/bw c/ar)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/au)      [(c/mk c/bw c/au)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/ad)      [(c/mk c/bw c/ad)]]

    ^{:doc/actions [{}]} [(c/mk c/bwsp c/al)     [(c/mk c/bws c/al)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/ar)     [(c/mk c/bws c/ar)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/au)     [(c/mk c/bws c/au)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/ad)     [(c/mk c/bws c/ad)]]

    ; technical glyphs
    ^{:doc/actions [{}]} [(c/mk c/bwp c/ob)      [(c/mk c/bw c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/cb)      [(c/mk c/bw c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/sc)      [(c/mk c/bw c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/qu)      [(c/mk c/bw c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/bl)      [(c/mk c/bw c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/cm)      [(c/mk c/bw c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/pe)      [(c/mk c/bw c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/sl)      [(c/mk c/bw c/sl)]]

    ^{:doc/actions [{}]} [(c/mk c/bwsp c/ob)     [(c/mk c/bws c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/cb)     [(c/mk c/bws c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/sc)     [(c/mk c/bws c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/qu)     [(c/mk c/bws c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/bl)     [(c/mk c/bws c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/cm)     [(c/mk c/bws c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/pe)     [(c/mk c/bws c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/sl)     [(c/mk c/bws c/sl)]]

    ; TODO: annotate command line keys => history picker, etc
    ; action glyphs
    ^{:doc/actions [{:program c/hc,    :action "changed file picker",:exec hc-change-picker}
                    {:program c/mc,    :action "open file",          :exec mc-open-file}]}         [(c/mk c/bw c/db) [:!Tr] :term]
    ^{:doc/actions [{:program c/hc,    :action "file picker",        :exec hc-file-picker}
                    {:program c/mc,    :action "open new file",      :exec mc-new-open-file}]}     [(c/mk c/bw c/re) [:!Ts] :term]
    ^{:doc/actions [{:program c/hc,    :action "buffer picker",      :exec hc-buffer-picker}
                    {:program c/mc,    :action "command mode",       :exec mc-cmd}]}               [(c/mk c/bw c/rs) [:!Tt] :term]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/ro)      [(c/mk c/bw c/ro)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/rc)      [(c/mk c/bw c/rc)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/sp)      [(c/mk c/bw c/sp)]]

    ^{:doc/actions [{}]} [(c/mk c/bwsp c/db)     [(c/mk c/bws c/db)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/re)     [(c/mk c/bws c/re)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/rs)     [(c/mk c/bws c/rs)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/ro)     [(c/mk c/bws c/ro)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/rc)     [(c/mk c/bws c/rc)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/sp)     [(c/mk c/bws c/sp)]]

    ; numeric-glyphs
    ^{:doc/actions [{}]} [(c/mk c/bwp "1")       [(c/mk c/bw "1")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "2")       [(c/mk c/bw "2")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "3")       [(c/mk c/bw "3")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "4")       [(c/mk c/bw "4")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "5")       [(c/mk c/bw "5")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "6")       [(c/mk c/bw "6")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "7")       [(c/mk c/bw "7")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "8")       [(c/mk c/bw "8")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "9")       [(c/mk c/bw "9")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "0")       [(c/mk c/bw "0")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/hy)      [(c/mk c/bw c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/eq)      [(c/mk c/bw c/eq)]]

    ^{:doc/actions [{}]} [(c/mk c/bwsp "1")      [(c/mk c/bws "1")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "2")      [(c/mk c/bws "2")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "3")      [(c/mk c/bws "3")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "4")      [(c/mk c/bws "4")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "5")      [(c/mk c/bws "5")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "6")      [(c/mk c/bws "6")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "7")      [(c/mk c/bws "7")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "8")      [(c/mk c/bws "8")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "9")      [(c/mk c/bws "9")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "0")      [(c/mk c/bws "0")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/hy)     [(c/mk c/bws c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/eq)     [(c/mk c/bws c/eq)]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]} [(c/mk c/bwp "a")       [(c/mk c/bw "a")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "b")       [(c/mk c/bw "b")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "c")       [(c/mk c/bw "c")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "d")       [(c/mk c/bw "d")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "e")       [(c/mk c/bw "e")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "f")       [(c/mk c/bw "f")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "g")       [(c/mk c/bw "g")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "h")       [(c/mk c/bw "h")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "i")       [(c/mk c/bw "i")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "j")       [(c/mk c/bw "j")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "k")       [(c/mk c/bw "k")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "l")       [(c/mk c/bw "l")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "m")       [(c/mk c/bw "m")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "n")       [(c/mk c/bw "n")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "o")       [(c/mk c/bw "o")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "p")       [(c/mk c/bw "p")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "q")       [(c/mk c/bw "q")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "r")       [(c/mk c/bw "r")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "s")       [(c/mk c/bw "s")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "t")       [(c/mk c/bw "t")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "u")       [(c/mk c/bw "u")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "v")       [(c/mk c/bw "v")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "w")       [(c/mk c/bw "w")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "x")       [(c/mk c/bw "x")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "y")       [(c/mk c/bw "y")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp "z")       [(c/mk c/bw "z")]]
    ^{:doc/actions [{}]} [(c/mk c/bwp c/rt)      [(c/mk c/bw c/rt)]]

    ^{:doc/actions [{}]} [(c/mk c/bwsp "a")      [(c/mk c/bws "a")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "b")      [(c/mk c/bws "b")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "c")      [(c/mk c/bws "c")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "d")      [(c/mk c/bws "d")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "e")      [(c/mk c/bws "e")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "f")      [(c/mk c/bws "f")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "g")      [(c/mk c/bws "g")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "h")      [(c/mk c/bws "h")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "i")      [(c/mk c/bws "i")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "j")      [(c/mk c/bws "j")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "k")      [(c/mk c/bws "k")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "l")      [(c/mk c/bws "l")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "m")      [(c/mk c/bws "m")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "n")      [(c/mk c/bws "n")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "o")      [(c/mk c/bws "o")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "p")      [(c/mk c/bws "p")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "q")      [(c/mk c/bws "q")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "r")      [(c/mk c/bws "r")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "s")      [(c/mk c/bws "s")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "t")      [(c/mk c/bws "t")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "u")      [(c/mk c/bws "u")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "v")      [(c/mk c/bws "v")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "w")      [(c/mk c/bws "w")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "x")      [(c/mk c/bws "x")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "y")      [(c/mk c/bws "y")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp "z")      [(c/mk c/bws "z")]]
    ^{:doc/actions [{}]} [(c/mk c/bwsp c/rt)     [(c/mk c/bws c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
