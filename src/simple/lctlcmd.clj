;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL-CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lctlcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lctlcmd.edn")

(def hc-prev-tab             ["goto_previous_buffer"])
(def lg-prev-tab             ["prevTab"])
(def mc-prev-tab             ["PreviousTab"])
(def ze-move-left            ["MovePane \"Left\";"])
(def hc-next-tab             ["goto_next_buffer"])
(def lg-next-tab             ["nextTab"])
(def mc-next-tab             ["NextTab"])
(def ze-move-rigth           ["MovePane \"Right\";"])
(def hc-inc                  ["increment"])
(def lg-prev-block           ["prevBlock-alt2"])
(def ze-move-up              ["MovePane \"Up\";"])
(def hc-dec                  ["decrement"])
(def lg-next-block           ["nextBlock-alt2"])
(def ze-move-down            ["MovePane \"Down\";"])
(def hc-close-tab            [":buffer-close"])
(def mc-close-tab            ["Quit"])
(def hc-unsplit              ["wonly"])
(def mc-unsplit              ["Unsplit"])
(def hc-vsplit               ["vsplit"])
(def mc-vsplit               ["VSplit"])
(def hc-hsplit               ["hsplit"])
(def mc-hsplit               ["HSplit"])
(def hc-close-split          ["wclose"])
(def mc-close-split          ["Unsplit"])
(def hc-last-tab             ["goto_last_accessed_file"])
(def br-toggle-preview       [])

(defn lctlcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control - Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "jump prev buffer",   :exec hc-prev-tab}
                    {:program c/lg,    :action "jump prev tab",      :exec lg-prev-tab}
                    {:program c/mc,    :action "jump prev buffer",   :exec mc-prev-tab}
                    {:program c/ze,    :action "move left",          :exec ze-move-left}]}         [(c/mk c/btcp c/al) [:!Tb] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump next buffer",   :exec hc-next-tab}
                    {:program c/lg,    :action "jump next tab",      :exec lg-next-tab}
                    {:program c/mc,    :action "jump next buffer",   :exec mc-next-tab}
                    {:program c/ze,    :action "move right",         :exec ze-move-rigth}]}        [(c/mk c/btcp c/ar) [:!Tf] :term]
    ^{:doc/actions [{:program c/hc,    :action "increment number",   :exec hc-inc}
                    {:program c/lg,    :action "jump prev block",    :exec lg-prev-block}
                    {:program c/ze,    :action "move up",            :exec ze-move-up}]}           [(c/mk c/btcp c/au) [:!Tn] :term]
    ^{:doc/actions [{:program c/hc,    :action "decrement number",   :exec hc-dec}
                    {:program c/lg,    :action "jump next block",    :exec lg-next-block}
                    {:program c/ze,    :action "move down",          :exec ze-move-down}]}         [(c/mk c/btcp c/ad) [:!Tp] :term]

    ^{:doc/actions [{}]} [(c/mk c/btcsp c/al)    [(c/mk c/btcs c/al)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/ar)    [(c/mk c/btcs c/ar)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/au)    [(c/mk c/btcs c/au)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/ad)    [(c/mk c/btcs c/ad)]]

    ; technical glyphs
    ^{:doc/actions [{}]} [(c/mk c/btcp c/ob)     [(c/mk c/btc c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/cb)     [(c/mk c/btc c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/sc)     [(c/mk c/btc c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/qu)     [(c/mk c/btc c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/bl)     [(c/mk c/btc c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/cm)     [(c/mk c/btc c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/pe)     [(c/mk c/btc c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/sl)     [(c/mk c/btc c/sl)]]

    ^{:doc/actions [{}]} [(c/mk c/btcsp c/ob)    [(c/mk c/btcs c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/cb)    [(c/mk c/btcs c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/sc)    [(c/mk c/btcs c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/qu)    [(c/mk c/btcs c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/bl)    [(c/mk c/btcs c/bl)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/cm)    [(c/mk c/btcs c/cm)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/pe)    [(c/mk c/btcs c/pe)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/sl)    [(c/mk c/btcs c/sl)]]

    ; action glyphs
    ^{:doc/actions [{:program c/hc,    :action "close tab",          :exec hc-close-tab}
                    {:program c/mc,    :action "close tab",          :exec mc-close-tab}]}         [(c/mk c/btcp c/db) [:!Tl] :term]
    ^{:doc/actions [{:program c/hc,    :action "close others",       :exec hc-unsplit}
                    {:program c/mc,    :action "close others",       :exec mc-unsplit}]}           [(c/mk c/btcp c/re) [:!Tg] :term]
    ^{:doc/actions [{:program c/hc,    :action "split right",        :exec hc-vsplit}
                    {:program c/mc,    :action "split right",        :exec mc-vsplit}]}            [(c/mk c/btcp c/rs) [:!Tv] :term]
    ^{:doc/actions [{:program c/hc,    :action "split down",         :exec hc-hsplit}
                    {:program c/mc,    :action "split down",         :exec mc-hsplit}]}            [(c/mk c/btcp c/ro) [:!Th] :term]
    ^{:doc/actions [{:program c/hc,    :action "close window",       :exec hc-close-split}
                    {:program c/mc,    :action "close window",       :exec mc-close-split}]}       [(c/mk c/btcp c/rc) [:!Tj] :term]
    ^{:doc/actions [{:program c/hc,    :action "last file",          :exec hc-last-tab}
                    {:program c/br,    :action "open preview"        :exec br-toggle-preview}]}    [(c/mk c/btcp c/sp) [:!To] :term]

    ^{:doc/actions [{}]} [(c/mk c/btcsp c/db)    [(c/mk c/btcs c/db)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/re)    [(c/mk c/btcs c/re)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/rs)    [(c/mk c/btcs c/rs)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/ro)    [(c/mk c/btcs c/ro)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/rc)    [(c/mk c/btcs c/rc)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/sp)    [(c/mk c/btcs c/sp)]]

    ; numeric-glyphs
    ^{:doc/actions [{}]} [(c/mk c/btcp "1")      [(c/mk c/btc "1")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "2")      [(c/mk c/btc "2")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "3")      [(c/mk c/btc "3")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "4")      [(c/mk c/btc "4")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "5")      [(c/mk c/btc "5")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "6")      [(c/mk c/btc "6")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "7")      [(c/mk c/btc "7")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "8")      [(c/mk c/btc "8")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "9")      [(c/mk c/btc "9")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "0")      [(c/mk c/btc "0")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/hy)     [(c/mk c/btc c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/eq)     [(c/mk c/btc c/eq)]]

    ^{:doc/actions [{}]} [(c/mk c/btcsp "1")     [(c/mk c/btcs "1")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "2")     [(c/mk c/btcs "2")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "3")     [(c/mk c/btcs "3")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "4")     [(c/mk c/btcs "4")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "5")     [(c/mk c/btcs "5")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "6")     [(c/mk c/btcs "6")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "7")     [(c/mk c/btcs "7")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "8")     [(c/mk c/btcs "8")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "9")     [(c/mk c/btcs "9")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "0")     [(c/mk c/btcs "0")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/hy)    [(c/mk c/btcs c/hy)]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/eq)    [(c/mk c/btcs c/eq)]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]} [(c/mk c/btcp "a")      [(c/mk c/btc "a")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "b")      [(c/mk c/btc "b")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "c")      [(c/mk c/btc "c")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "d")      [(c/mk c/btc "d")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "e")      [(c/mk c/btc "e")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "f")      [(c/mk c/btc "f")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "g")      [(c/mk c/btc "g")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "h")      [(c/mk c/btc "h")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "i")      [(c/mk c/btc "i")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "j")      [(c/mk c/btc "j")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "k")      [(c/mk c/btc "k")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "l")      [(c/mk c/btc "l")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "m")      [(c/mk c/btc "m")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "n")      [(c/mk c/btc "n")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "o")      [(c/mk c/btc "o")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "p")      [(c/mk c/btc "p")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "q")      [(c/mk c/btc "q")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "r")      [(c/mk c/btc "r")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "s")      [(c/mk c/btc "s")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "t")      [(c/mk c/btc "t")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "u")      [(c/mk c/btc "u")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "v")      [(c/mk c/btc "v")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "w")      [(c/mk c/btc "w")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "x")      [(c/mk c/btc "x")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "y")      [(c/mk c/btc "y")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp "z")      [(c/mk c/btc "z")]]
    ^{:doc/actions [{}]} [(c/mk c/btcp c/rt)     [(c/mk c/btc c/rt)]]

    ^{:doc/actions [{}]} [(c/mk c/btcsp "a")     [(c/mk c/btcs "a")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "b")     [(c/mk c/btcs "b")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "c")     [(c/mk c/btcs "c")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "d")     [(c/mk c/btcs "d")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "e")     [(c/mk c/btcs "e")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "f")     [(c/mk c/btcs "f")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "g")     [(c/mk c/btcs "g")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "h")     [(c/mk c/btcs "h")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "i")     [(c/mk c/btcs "i")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "j")     [(c/mk c/btcs "j")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "k")     [(c/mk c/btcs "k")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "l")     [(c/mk c/btcs "l")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "m")     [(c/mk c/btcs "m")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "n")     [(c/mk c/btcs "n")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "o")     [(c/mk c/btcs "o")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "p")     [(c/mk c/btcs "p")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "q")     [(c/mk c/btcs "q")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "r")     [(c/mk c/btcs "r")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "s")     [(c/mk c/btcs "s")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "t")     [(c/mk c/btcs "t")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "u")     [(c/mk c/btcs "u")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "v")     [(c/mk c/btcs "v")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "w")     [(c/mk c/btcs "w")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "x")     [(c/mk c/btcs "x")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "y")     [(c/mk c/btcs "y")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp "z")     [(c/mk c/btcs "z")]]
    ^{:doc/actions [{}]} [(c/mk c/btcsp c/rt)    [(c/mk c/btcs c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lctlcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
