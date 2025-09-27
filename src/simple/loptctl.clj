;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT-CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.loptctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "loptctl.edn")

(def zj-pane-left            ["MoveFocus \"Left\";"])
(def zj-pane-right           ["MoveFocus \"Right\";"])
(def zj-pane-up              ["MoveFocus \"Up\";"])
(def zj-pane-down            ["MoveFocus \"Down\";"])
(def zj-pane-close           ["CloseFocus;"])
(def zj-pane-focus           ["ToggleFocusFullscreen;"])
(def zj-pane-new-right       ["NewPane \"Right\";"])
(def zj-pane-new-down        ["NewPane \"Down\";"])
(def zj-rename-pane-mode     ["SwitchToMode \"RenamePane\"; PaneNameInput 0;"])
(def zp-abort-rename         ["UndoRenamePane; SwitchToMode \"Normal\";"])
(def zj-last-pane            ["SwitchFocus;"])

(defn loptctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option - Control Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "pane left",          :exec zj-pane-left}]}         [(c/mk c/botp c/al) [:!Oj]    :term]
    ^{:doc/actions [{:program c/zj,    :action "pane right",         :exec zj-pane-right}]}        [(c/mk c/botp c/ar) [:!Ok]    :term]
    ^{:doc/actions [{:program c/zj,    :action "pane up",            :exec zj-pane-up}]}           [(c/mk c/botp c/au) [:!Ol]    :term]
    ^{:doc/actions [{:program c/zj,    :action "pane down",          :exec zj-pane-down}]}         [(c/mk c/botp c/ad) [:!Om]    :term]

    ^{:doc/actions [{}]} [(c/mk c/botsp c/al)    [(c/mk c/bots c/al)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/ar)    [(c/mk c/bots c/ar)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/au)    [(c/mk c/bots c/au)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/ad)    [(c/mk c/bots c/ad)]]

    ; technical glyphs
    ^{:doc/actions [{}]} [(c/mk c/botp c/ob)     [(c/mk c/bot c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/cb)     [(c/mk c/bot c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/sc)     [(c/mk c/bot c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/qu)     [(c/mk c/bot c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/bl)     [(c/mk c/bot c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/cm)     [(c/mk c/bot c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/pe)     [(c/mk c/bot c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/sl)     [(c/mk c/bot c/sl)]]

    ^{:doc/actions [{}]} [(c/mk c/botsp c/ob)    [(c/mk c/bots c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/cb)    [(c/mk c/bots c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/sc)    [(c/mk c/bots c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/qu)    [(c/mk c/bots c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/bl)    [(c/mk c/bots c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/cm)    [(c/mk c/bots c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/pe)    [(c/mk c/bots c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/sl)    [(c/mk c/bots c/sl)]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj,    :action "pane close",         :exec zj-pane-close}]}        [(c/mk c/botp c/db) [:!Oo]    :term]
    ^{:doc/actions [{:program c/zj,    :action "pane focus",         :exec zj-pane-focus}]}        [(c/mk c/botp c/re) [:!Op]    :term]
    ^{:doc/actions [{:program c/zj,    :action "pane split right",   :exec zj-pane-new-right}]}    [(c/mk c/botp c/rs) [:!Oq]    :term]
    ^{:doc/actions [{:program c/zj,    :action "pane split down",    :exec zj-pane-new-down}]}     [(c/mk c/botp c/ro) [:!Or]    :term]
    ^{:doc/actions [{:program c/zj,    :action "mode pane rename",   :exec zj-rename-pane-mode}
                    {:program c/zp,    :action "abort pane rename",  :exec zp-abort-rename}]}      [(c/mk c/botp c/rc) [:!Os]    :term]
    ^{:doc/actions [{:program c/zj,    :action "pane jump back",     :exec zj-last-pane}]}         [(c/mk c/botp c/sp) [:!Ot]    :term]

    ^{:doc/actions [{}]} [(c/mk c/botsp c/db)    [(c/mk c/bots c/db)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/re)    [(c/mk c/bots c/re)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/rs)    [(c/mk c/bots c/rs)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/ro)    [(c/mk c/bots c/ro)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/rc)    [(c/mk c/bots c/rc)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/sp)    [(c/mk c/bots c/sp)]]

    ; numeric glyphs
    ^{:doc/actions [{}]} [(c/mk c/botp "1")      [(c/mk c/bot "1")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "2")      [(c/mk c/bot "2")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "3")      [(c/mk c/bot "3")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "4")      [(c/mk c/bot "4")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "5")      [(c/mk c/bot "5")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "6")      [(c/mk c/bot "6")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "7")      [(c/mk c/bot "7")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "8")      [(c/mk c/bot "8")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "9")      [(c/mk c/bot "9")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "0")      [(c/mk c/bot "0")]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/hy)     [(c/mk c/bot c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/eq)     [(c/mk c/bot c/eq)]]

    ^{:doc/actions [{}]} [(c/mk c/botsp "1")     [(c/mk c/bots "1")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "2")     [(c/mk c/bots "2")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "3")     [(c/mk c/bots "3")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "4")     [(c/mk c/bots "4")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "5")     [(c/mk c/bots "5")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "6")     [(c/mk c/bots "6")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "7")     [(c/mk c/bots "7")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "8")     [(c/mk c/bots "8")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "9")     [(c/mk c/bots "9")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "0")     [(c/mk c/bots "0")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/hy)    [(c/mk c/bots c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/eq)    [(c/mk c/bots c/eq)]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]} [(c/mk c/botp "a")      [(c/mk c/bot "a")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "b")      [(c/mk c/bot "b")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "c")      [(c/mk c/bot "c")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "d")      [(c/mk c/bot "d")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "e")      [(c/mk c/bot "e")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "f")      [(c/mk c/bot "f")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "g")      [(c/mk c/bot "g")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "h")      [(c/mk c/bot "h")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "i")      [(c/mk c/bot "i")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "j")      [(c/mk c/bot "j")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "k")      [(c/mk c/bot "k")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "l")      [(c/mk c/bot "l")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "m")      [(c/mk c/bot "m")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "n")      [(c/mk c/bot "n")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "o")      [(c/mk c/bot "o")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "p")      [(c/mk c/bot "p")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "q")      [(c/mk c/bot "q")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "r")      [(c/mk c/bot "r")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "s")      [(c/mk c/bot "s")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "t")      [(c/mk c/bot "t")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "u")      [(c/mk c/bot "u")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "v")      [(c/mk c/bot "v")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "w")      [(c/mk c/bot "w")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "x")      [(c/mk c/bot "x")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "y")      [(c/mk c/bot "y")]]
    ^{:doc/actions [{}]} [(c/mk c/botp "z")      [(c/mk c/bot "z")]]
    ^{:doc/actions [{}]} [(c/mk c/botp c/rt)     [(c/mk c/bot c/rt)]]

    ^{:doc/actions [{}]} [(c/mk c/botsp "a")     [(c/mk c/bots "a")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "b")     [(c/mk c/bots "b")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "c")     [(c/mk c/bots "c")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "d")     [(c/mk c/bots "d")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "e")     [(c/mk c/bots "e")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "f")     [(c/mk c/bots "f")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "g")     [(c/mk c/bots "g")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "h")     [(c/mk c/bots "h")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "i")     [(c/mk c/bots "i")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "j")     [(c/mk c/bots "j")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "k")     [(c/mk c/bots "k")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "l")     [(c/mk c/bots "l")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "m")     [(c/mk c/bots "m")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "n")     [(c/mk c/bots "n")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "o")     [(c/mk c/bots "o")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "p")     [(c/mk c/bots "p")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "q")     [(c/mk c/bots "q")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "r")     [(c/mk c/bots "r")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "s")     [(c/mk c/bots "s")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "t")     [(c/mk c/bots "t")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "u")     [(c/mk c/bots "u")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "v")     [(c/mk c/bots "v")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "w")     [(c/mk c/bots "w")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "x")     [(c/mk c/bots "x")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "y")     [(c/mk c/bots "y")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp "z")     [(c/mk c/bots "z")]]
    ^{:doc/actions [{}]} [(c/mk c/botsp c/rt)    [(c/mk c/bots c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (loptctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
