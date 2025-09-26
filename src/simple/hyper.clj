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
(def ze-size-inc ["Resize \"Increase\";"])
(def ze-size-dec ["Resize \"Decrease\";"])
(def zj-toggle-pin ["TogglePanePinned;"])
(def zj-toggle-float ["ToggleFloatingPanes;"])
(def zj-entersearch-mode ["SwitchToMode \"EnterSearch\"; SearchInput 0;"])
(def zj-toggle-embed ["TogglePaneEmbedOrFloating;"])
(def zj-locked-mode ["SwitchToMode \"Locked\";"])
(def z-normal-mode ["SwitchToMode \"Normal\";"])

(defn hyper []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Hyper Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "page up",            :exec zj-swap-tab-left}]}     [(c/mk c/botcp c/al) [c/kspu] :term]
    ^{:doc/actions [{:program c/zj,    :action "page down",          :exec zj-swap-tab-right}]}    [(c/mk c/botcp c/ar) [c/kspd] :term]
    ^{:doc/actions [{:program c/ze,    :action "home",               :exec ze-size-inc}]}          [(c/mk c/botcp c/au) [c/kshm] :term]
    ^{:doc/actions [{:program c/ze,    :action "end",                :exec ze-size-dec}]}          [(c/mk c/botcp c/ad) [c/ksed] :term]

    ^{:doc/actions [{}]} [(c/mk c/botcsp c/al)   [(c/mk c/botcs c/al)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/ar)   [(c/mk c/botcs c/ar)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/au)   [(c/mk c/botcs c/au)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/ad)   [(c/mk c/botcs c/ad)]]

    ; technical glyphs
    ^{:doc/actions [{}]} [(c/mk c/botcp c/ob)    [(c/mk c/botc c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/cb)    [(c/mk c/botc c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/sc)    [(c/mk c/botc c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/qu)    [(c/mk c/botc c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/bl)    [(c/mk c/botc c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/cm)    [(c/mk c/botc c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/pe)    [(c/mk c/botc c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/sl)    [(c/mk c/botc c/sl)]]

    ^{:doc/actions [{}]} [(c/mk c/botcsp c/ob)   [(c/mk c/botcs c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/cb)   [(c/mk c/botcs c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/sc)   [(c/mk c/botcs c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/qu)   [(c/mk c/botcs c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/bl)   [(c/mk c/botcs c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/cm)   [(c/mk c/botcs c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/pe)   [(c/mk c/botcs c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/sl)   [(c/mk c/botcs c/sl)]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj, :action "pane float pin",    :exec zj-toggle-pin}]}            [(c/mk c/botcp c/db) [:!SOm]  :term]
    ^{:doc/actions [{:program c/zj, :action "pane float toggle", :exec zj-toggle-float}]}          [(c/mk c/botcp c/re) [:!SOn]  :term]
    ^{:doc/actions [{:program c/zj, :action "mode search",       :exec zj-entersearch-mode}
                    {:program c/ze, :action "mode normal",       :exec z-normal-mode}]}            [(c/mk c/botcp c/rs) [:!Oh]   :term]
    ^{:doc/actions [{:program c/zj, :action "pane float pop",    :exec zj-toggle-embed}]}          [(c/mk c/botcp c/ro) [:!SOl]  :term]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/rc)    [(c/mk c/botc c/rc)]]
    ^{:doc/actions [{:program c/zj, :action "mode lock",         :exec zj-locked-mode}
                    {:program c/zl, :action "mode normal",       :exec z-normal-mode}]}            [(c/mk c/botcp c/sp) [:!Og]   :term]

    ^{:doc/actions [{}]} [(c/mk c/botcsp c/db)   [(c/mk c/botcs c/db)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/re)   [(c/mk c/botcs c/re)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/rs)   [(c/mk c/botcs c/rs)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/ro)   [(c/mk c/botcs c/ro)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/rc)   [(c/mk c/botcs c/rc)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/sp)   [(c/mk c/botcs c/sp)]]

    ; numeric-glyphs
    ^{:doc/actions [{}]} [(c/mk c/botcp "1")     [(c/mk c/botc "1")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "2")     [(c/mk c/botc "2")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "3")     [(c/mk c/botc "3")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "4")     [(c/mk c/botc "4")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "5")     [(c/mk c/botc "5")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "6")     [(c/mk c/botc "6")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "7")     [(c/mk c/botc "7")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "8")     [(c/mk c/botc "8")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "9")     [(c/mk c/botc "9")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "0")     [(c/mk c/botc "0")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/hy)    [(c/mk c/botc c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/eq)    [(c/mk c/botc c/eq)]]

    ^{:doc/actions [{}]} [(c/mk c/botcsp "1")    [(c/mk c/botcs "1")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "2")    [(c/mk c/botcs "2")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "3")    [(c/mk c/botcs "3")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "4")    [(c/mk c/botcs "4")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "5")    [(c/mk c/botcs "5")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "6")    [(c/mk c/botcs "6")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "7")    [(c/mk c/botcs "7")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "8")    [(c/mk c/botcs "8")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "9")    [(c/mk c/botcs "9")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "0")    [(c/mk c/botcs "0")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/hy)   [(c/mk c/botcs c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/eq)   [(c/mk c/botcs c/eq)]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]} [(c/mk c/botcp "a")     [(c/mk c/botc "a")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "b")     [(c/mk c/botc "b")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "c")     [(c/mk c/botc "c")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "d")     [(c/mk c/botc "d")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "e")     [(c/mk c/botc "e")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "f")     [(c/mk c/botc "f")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "g")     [(c/mk c/botc "g")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "h")     [(c/mk c/botc "h")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "i")     [(c/mk c/botc "i")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "j")     [(c/mk c/botc "j")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "k")     [(c/mk c/botc "k")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "l")     [(c/mk c/botc "l")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "m")     [(c/mk c/botc "m")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "n")     [(c/mk c/botc "n")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "o")     [(c/mk c/botc "o")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "p")     [(c/mk c/botc "p")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "q")     [(c/mk c/botc "q")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "r")     [(c/mk c/botc "r")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "s")     [(c/mk c/botc "s")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "t")     [(c/mk c/botc "t")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "u")     [(c/mk c/botc "u")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "v")     [(c/mk c/botc "v")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "w")     [(c/mk c/botc "w")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "x")     [(c/mk c/botc "x")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "y")     [(c/mk c/botc "y")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp "z")     [(c/mk c/botc "z")]]
    ^{:doc/actions [{}]} [(c/mk c/botcp c/rt)    [(c/mk c/botc c/rt)]]

    ^{:doc/actions [{}]} [(c/mk c/botcsp "a")    [(c/mk c/botcs "a")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "b")    [(c/mk c/botcs "b")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "c")    [(c/mk c/botcs "c")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "d")    [(c/mk c/botcs "d")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "e")    [(c/mk c/botcs "e")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "f")    [(c/mk c/botcs "f")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "g")    [(c/mk c/botcs "g")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "h")    [(c/mk c/botcs "h")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "i")    [(c/mk c/botcs "i")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "j")    [(c/mk c/botcs "j")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "k")    [(c/mk c/botcs "k")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "l")    [(c/mk c/botcs "l")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "m")    [(c/mk c/botcs "m")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "n")    [(c/mk c/botcs "n")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "o")    [(c/mk c/botcs "o")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "p")    [(c/mk c/botcs "p")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "q")    [(c/mk c/botcs "q")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "r")    [(c/mk c/botcs "r")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "s")    [(c/mk c/botcs "s")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "t")    [(c/mk c/botcs "t")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "u")    [(c/mk c/botcs "u")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "v")    [(c/mk c/botcs "v")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "w")    [(c/mk c/botcs "w")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "x")    [(c/mk c/botcs "x")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "y")    [(c/mk c/botcs "y")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp "z")    [(c/mk c/botcs "z")]]
    ^{:doc/actions [{}]} [(c/mk c/botcsp c/rt)   [(c/mk c/botcs c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (hyper)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
