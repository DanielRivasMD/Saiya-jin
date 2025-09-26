;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; HYPER => OTC
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.hyper
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "hyper.edn")

(def zj-swap-tab-left ["MoveTab \"Left\";"])
(def zj-swap-tab-right ["MoveTab \"Right\";"])
(def zj-size-inc ["Resize \"Increase\";"])
(def zj-size-dec ["Resize \"Decrease\";"])
(def zj-toggle-pin ["TogglePanePinned;"])
(def zj-toggle-float ["ToggleFloatingPanes;"])
(def zj-entersearch ["SwitchToMode \"EnterSearch\"; SearchInput 0;"])
(def zj-toggle-embed ["TogglePaneEmbedOrFloating;"])
(def zj-locked ["SwitchToMode \"Locked\";"])
(def zj-normal ["SwitchToMode \"Normal\";"])

(defn hyper []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Hyper Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "page up",            :exec zj-swap-tab-left}]}     [(c/mk c/ihyper c/al) [c/kspu] :term]
    ^{:doc/actions [{:program c/zj,    :action "page down",          :exec zj-swap-tab-right}]}    [(c/mk c/ihyper c/ar) [c/kspd] :term]
    ^{:doc/actions [{:program c/zj,    :action "home",               :exec zj-size-inc}]}          [(c/mk c/ihyper c/au) [c/kshm] :term]
    ^{:doc/actions [{:program c/zj,    :action "end",                :exec zj-size-dec}]}          [(c/mk c/ihyper c/ad) [c/ksed] :term]

    ^{:doc/actions [{}]} [(c/mk c/ihypers c/al)  [(c/mk c/ohypers c/al)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/ar)  [(c/mk c/ohypers c/ar)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/au)  [(c/mk c/ohypers c/au)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/ad)  [(c/mk c/ohypers c/ad)]]

    ; technical glyphs
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/ob)   [(c/mk c/ohyper c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/cb)   [(c/mk c/ohyper c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/sc)   [(c/mk c/ohyper c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/qu)   [(c/mk c/ohyper c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/bl)   [(c/mk c/ohyper c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/cm)   [(c/mk c/ohyper c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/pe)   [(c/mk c/ohyper c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/sl)   [(c/mk c/ohyper c/sl)]]

    ^{:doc/actions [{}]} [(c/mk c/ihypers c/ob)  [(c/mk c/ohypers c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/cb)  [(c/mk c/ohypers c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/sc)  [(c/mk c/ohypers c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/qu)  [(c/mk c/ohypers c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/bl)  [(c/mk c/ohypers c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/cm)  [(c/mk c/ohypers c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/pe)  [(c/mk c/ohypers c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/sl)  [(c/mk c/ohypers c/sl)]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj, :action "pane float pin",    :exec zj-toggle-pin}]}            [(c/mk c/ihyper c/db) [:!SOm]  :term]
    ^{:doc/actions [{:program c/zj, :action "pane float toggle", :exec zj-toggle-float}]}          [(c/mk c/ihyper c/re) [:!SOn]  :term]
    ^{:doc/actions [{:program c/zj, :action "mode search",       :exec zj-entersearch}]}           [(c/mk c/ihyper c/rs) [:!Oh]   :term]
    ^{:doc/actions [{:program c/zj, :action "pane float pop",    :exec zj-toggle-embed}]}          [(c/mk c/ihyper c/ro) [:!SOl]  :term]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/rc)   [(c/mk c/ohyper c/rc)]]
    ^{:doc/actions [{:program c/zj, :action "mode lock",         :exec zj-locked}
                    {:program c/zj, :action "mode normal",       :exec zj-normal}]}                [(c/mk c/ihyper c/sp) [:!Og]   :term]

    ^{:doc/actions [{}]} [(c/mk c/ihypers c/db)  [(c/mk c/ohypers c/db)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/re)  [(c/mk c/ohypers c/re)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/rs)  [(c/mk c/ohypers c/rs)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/ro)  [(c/mk c/ohypers c/ro)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/rc)  [(c/mk c/ohypers c/rc)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/sp)  [(c/mk c/ohypers c/sp)]]

    ; numeric-glyphs
    ^{:doc/actions [{}]} [(c/mk c/ihyper "1")    [(c/mk c/ohyper "1")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "2")    [(c/mk c/ohyper "2")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "3")    [(c/mk c/ohyper "3")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "4")    [(c/mk c/ohyper "4")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "5")    [(c/mk c/ohyper "5")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "6")    [(c/mk c/ohyper "6")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "7")    [(c/mk c/ohyper "7")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "8")    [(c/mk c/ohyper "8")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "9")    [(c/mk c/ohyper "9")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "0")    [(c/mk c/ohyper "0")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/hy)   [(c/mk c/ohyper c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/eq)   [(c/mk c/ohyper c/eq)]]

    ^{:doc/actions [{}]} [(c/mk c/ihypers "1")   [(c/mk c/ohypers "1")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "2")   [(c/mk c/ohypers "2")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "3")   [(c/mk c/ohypers "3")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "4")   [(c/mk c/ohypers "4")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "5")   [(c/mk c/ohypers "5")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "6")   [(c/mk c/ohypers "6")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "7")   [(c/mk c/ohypers "7")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "8")   [(c/mk c/ohypers "8")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "9")   [(c/mk c/ohypers "9")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "0")   [(c/mk c/ohypers "0")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/hy)  [(c/mk c/ohypers c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/eq)  [(c/mk c/ohypers c/eq)]]

     ; alphabetic-glyphs
    ^{:doc/actions [{}]} [(c/mk c/ihyper "a")    [(c/mk c/ohyper "a")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "b")    [(c/mk c/ohyper "b")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "c")    [(c/mk c/ohyper "c")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "d")    [(c/mk c/ohyper "d")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "e")    [(c/mk c/ohyper "e")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "f")    [(c/mk c/ohyper "f")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "g")    [(c/mk c/ohyper "g")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "h")    [(c/mk c/ohyper "h")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "i")    [(c/mk c/ohyper "i")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "j")    [(c/mk c/ohyper "j")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "k")    [(c/mk c/ohyper "k")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "l")    [(c/mk c/ohyper "l")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "m")    [(c/mk c/ohyper "m")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "n")    [(c/mk c/ohyper "n")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "o")    [(c/mk c/ohyper "o")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "p")    [(c/mk c/ohyper "p")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "q")    [(c/mk c/ohyper "q")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "r")    [(c/mk c/ohyper "r")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "s")    [(c/mk c/ohyper "s")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "t")    [(c/mk c/ohyper "t")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "u")    [(c/mk c/ohyper "u")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "v")    [(c/mk c/ohyper "v")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "w")    [(c/mk c/ohyper "w")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "x")    [(c/mk c/ohyper "x")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "y")    [(c/mk c/ohyper "y")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper "z")    [(c/mk c/ohyper "z")]]
    ^{:doc/actions [{}]} [(c/mk c/ihyper c/rt)   [(c/mk c/ohyper c/rt)]]

    ^{:doc/actions [{}]} [(c/mk c/ihypers "a")   [(c/mk c/ohypers "a")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "b")   [(c/mk c/ohypers "b")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "c")   [(c/mk c/ohypers "c")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "d")   [(c/mk c/ohypers "d")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "e")   [(c/mk c/ohypers "e")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "f")   [(c/mk c/ohypers "f")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "g")   [(c/mk c/ohypers "g")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "h")   [(c/mk c/ohypers "h")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "i")   [(c/mk c/ohypers "i")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "j")   [(c/mk c/ohypers "j")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "k")   [(c/mk c/ohypers "k")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "l")   [(c/mk c/ohypers "l")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "m")   [(c/mk c/ohypers "m")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "n")   [(c/mk c/ohypers "n")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "o")   [(c/mk c/ohypers "o")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "p")   [(c/mk c/ohypers "p")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "q")   [(c/mk c/ohypers "q")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "r")   [(c/mk c/ohypers "r")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "s")   [(c/mk c/ohypers "s")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "t")   [(c/mk c/ohypers "t")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "u")   [(c/mk c/ohypers "u")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "v")   [(c/mk c/ohypers "v")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "w")   [(c/mk c/ohypers "w")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "x")   [(c/mk c/ohypers "x")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "y")   [(c/mk c/ohypers "y")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers "z")   [(c/mk c/ohypers "z")]]
    ^{:doc/actions [{}]} [(c/mk c/ihypers c/rt)  [(c/mk c/ohypers c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (hyper)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
