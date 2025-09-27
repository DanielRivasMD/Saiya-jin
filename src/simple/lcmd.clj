;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lcmd.edn")

(def hc-line-start           ["goto_line_start"])
(def mc-line-start           ["StartOfLine"])
(def hc-line-end             ["goto_line_end", "move_char_right"])
(def mc-line-end             ["EndOfLine"])
(def hc-file-start           ["goto_file_start"])
(def lg-top                  ["gotoTop"])
(def mc-file-start           ["CursorStart"])
(def ze-page-up              ["PageScrollUp"])
(def hc-file-end             ["goto_last_line"])
(def lg-bottom               ["gotoBottom"])
(def mc-file-end             ["CursorEnd"])
(def ze-page-down            ["PageScrollDown"])

(def hc-select-line-start    ["select_mode", "goto_line_start", "MODE"])
(def mc-select-line-start    ["SelectToStartOfLine"])
(def hc-select-line-end      ["select_mode", "goto_line_end", "MODE"])
(def mc-select-line-end      ["SelectToEndOfLine"])
(def hc-select-file-start    ["extend_to_file_start"])
(def mc-select-file-start    ["SelectToStart"])
(def hc-select-file-end      ["extend_to_file_end"])
(def mc-select-file-end      ["SelectToEnd"])

(defn lcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto line start",    :exec hc-line-start}
                    {:program c/mc,    :action "goto line start",    :exec mc-line-start}]}        [(c/mk c/bcp c/al)  [:!Ta]    :term]
    ^{:doc/actions [{:program c/hc,    :action "goto line end",      :exec hc-line-end}
                    {:program c/mc,    :action "goto line end",      :exec mc-line-end}]}          [(c/mk c/bcp c/ar)  [:!Te]    :term]
    ^{:doc/actions [{:program c/hc,    :action "goto file start",    :exec hc-file-start}
                    {:program c/lg,    :action "goto top",           :exec lg-top}
                    {:program c/mc,    :action "goto file start",    :exec mc-file-start}
                    {:program c/ze,    :action "page up",            :exec ze-page-up}]}           [(c/mk c/bcp c/au)  [c/khm]   :term]
    ^{:doc/actions [{:program c/hc,    :action "goto file end",      :exec hc-file-end}
                    {:program c/lg,    :action "goto bottom",        :exec lg-bottom}
                    {:program c/mc,    :action "goto file end",      :exec mc-file-end}
                    {:program c/ze,    :action "page down",          :exec ze-page-down}]}         [(c/mk c/bcp c/ad)  [c/ked]   :term]

    ^{:doc/actions [{:program c/hc,    :action "select line start",  :exec hc-select-line-start}
                    {:program c/mc,    :action "select line start",  :exec mc-select-line-start}]} [(c/mk c/bcsp c/al) [c/koal]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select line end",    :exec hc-select-line-end}
                    {:program c/mc,    :action "select line end",    :exec mc-select-line-end}]}   [(c/mk c/bcsp c/ar) [c/koar]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select file start",  :exec hc-select-file-start}
                    {:program c/mc,    :action "select file start",  :exec mc-select-file-start}]} [(c/mk c/bcsp c/au) [c/koau]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select file end",    :exec hc-select-file-end}
                    {:program c/mc,    :action "select file end",    :exec mc-select-file-end}]}   [(c/mk c/bcsp c/ad) [c/koad]  :term]

    ; TODO: annotate sequences
    ; technical glyphs
    ^{:doc/actions [{}]} [(c/mk c/bcp c/ob)      [(c/mk c/bc c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/cb)      [(c/mk c/bc c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/sc)      [(c/mk c/bc c/sc)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`::<`"}]}          [(c/mk c/bcp c/qu)  [:!Ssemicolon :!Ssemicolon :!Scomma]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/bl)      [(c/mk c/bc c/bl)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "' <- '"}]}         [(c/mk c/bcp c/cm)  [:spacebar :!Scomma :hyphen :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` -> `"}]}         [(c/mk c/bcp c/pe)  [:spacebar :hyphen :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`//! `"}]}         [(c/mk c/bcp c/sl)  [:slash :slash :!S1 :spacebar]]

    ^{:doc/actions [{}]} [(c/mk c/bcsp c/ob)     [(c/mk c/bcs c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/cb)     [(c/mk c/bcs c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/sc)     [(c/mk c/bcs c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/qu)     [(c/mk c/bcs c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/bl)     [(c/mk c/bcs c/bl)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` <<- `"}]}        [(c/mk c/bcsp c/cm) [:spacebar :!Scomma :!Scomma :hyphen :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ->> `"}]}        [(c/mk c/bcsp c/pe) [:spacebar :hyphen :!Speriod :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`#!/`"}]}          [(c/mk c/bcsp c/sl) [:!S3 :!S1 :slash]]

    ; action glyphs
    ^{:doc/actions [{}]} [(c/mk c/bcp c/db)      [(c/mk c/bc c/db)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/re)      [(c/mk c/bc c/re)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` != `"}]}        [(c/mk c/bcp c/rs)  [:spacebar :!S1 :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` *= `"}]}        [(c/mk c/bcp c/ro)  [:spacebar :!S8 :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` /= `"}]}        [(c/mk c/bcp c/rc)  [:spacebar :slash :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/sp)      [(c/mk c/bc c/sp)]]

    ^{:doc/actions [{}]} [(c/mk c/bcsp c/db)     [(c/mk c/bcs c/db)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "`; `"}]}          [(c/mk c/bcsp c/re) [:spacebar :!Ssemicolon :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` .!= `"}]}       [(c/mk c/bcsp c/rs) [:spacebar :period :!S1 :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` .*= `"}]}       [(c/mk c/bcsp c/ro) [:spacebar :period :!S8 :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` ./= `"}]}       [(c/mk c/bcsp c/rc) [:spacebar :period :slash :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/sp)     [(c/mk c/bcs c/sp)]]

    ; numeric glyphs
    ^{:doc/actions [{}]} [(c/mk c/bcp "1")       [(c/mk c/bc "1")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "2")       [(c/mk c/bc "2")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "3")       [(c/mk c/bc "3")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "4")       [(c/mk c/bc "4")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "5")       [(c/mk c/bc "5")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "6")       [(c/mk c/bc "6")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "7")       [(c/mk c/bc "7")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "8")       [(c/mk c/bc "8")]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence ".("}]}                [(c/mk c/bcp "9")   [:period :!S9]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "0")       [(c/mk c/bc "0")]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` -= `"}]}            [(c/mk c/bcp c/hy)  [:spacebar :hyphen :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` += `"}]}            [(c/mk c/bcp c/eq)  [:spacebar :!Sequal_sign :equal_sign :spacebar]]

    ^{:doc/actions [{}]} [(c/mk c/bcsp "1")      [(c/mk c/bcs "1")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "2")      [(c/mk c/bcs "2")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "3")      [(c/mk c/bcs "3")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "4")      [(c/mk c/bcs "4")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "5")      [(c/mk c/bcs "5")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "6")      [(c/mk c/bcs "6")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "7")      [(c/mk c/bcs "7")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "8")      [(c/mk c/bcs "8")]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "`:(`"}]}              [(c/mk c/bcsp "9")  [:!Ssemicolon :!S9]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "0")      [(c/mk c/bcs "0")]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` .-= `"}]}           [(c/mk c/bcsp c/hy) [:spacebar :period :hyphen :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` .+= `"}]}           [(c/mk c/bcsp c/eq) [:spacebar :period :!Sequal_sign :equal_sign :spacebar]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]} [(c/mk c/bcp "a")       [(c/mk c/bc "a")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "b")       [(c/mk c/bc "b")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "c")       [(c/mk c/bc "c")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "d")       [(c/mk c/bc "d")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "e")       [(c/mk c/bc "e")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "f")       [(c/mk c/bc "f")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "g")       [(c/mk c/bc "g")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "h")       [(c/mk c/bc "h")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "i")       [(c/mk c/bc "i")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "j")       [(c/mk c/bc "j")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "k")       [(c/mk c/bc "k")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "l")       [(c/mk c/bc "l")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "m")       [(c/mk c/bc "m")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "n")       [(c/mk c/bc "n")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "o")       [(c/mk c/bc "o")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "p")       [(c/mk c/bc "p")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "q")       [(c/mk c/bc "q")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "r")       [(c/mk c/bc "r")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "s")       [(c/mk c/bc "s")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "t")       [(c/mk c/bc "t")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "u")       [(c/mk c/bc "u")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "v")       [(c/mk c/bc "v")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "w")       [(c/mk c/bc "w")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "x")       [(c/mk c/bc "x")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "y")       [(c/mk c/bc "y")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp "z")       [(c/mk c/bc "z")]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/rt)      [(c/mk c/bc c/rt)]]

    ^{:doc/actions [{}]} [(c/mk c/bcsp "a")      [(c/mk c/bcs "a")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "b")      [(c/mk c/bcs "b")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "c")      [(c/mk c/bcs "c")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "d")      [(c/mk c/bcs "d")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "e")      [(c/mk c/bcs "e")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "f")      [(c/mk c/bcs "f")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "g")      [(c/mk c/bcs "g")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "h")      [(c/mk c/bcs "h")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "i")      [(c/mk c/bcs "i")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "j")      [(c/mk c/bcs "j")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "k")      [(c/mk c/bcs "k")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "l")      [(c/mk c/bcs "l")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "m")      [(c/mk c/bcs "m")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "n")      [(c/mk c/bcs "n")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "o")      [(c/mk c/bcs "o")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "p")      [(c/mk c/bcs "p")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "q")      [(c/mk c/bcs "q")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "r")      [(c/mk c/bcs "r")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "s")      [(c/mk c/bcs "s")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "t")      [(c/mk c/bcs "t")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "u")      [(c/mk c/bcs "u")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "v")      [(c/mk c/bcs "v")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "w")      [(c/mk c/bcs "w")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "x")      [(c/mk c/bcs "x")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "y")      [(c/mk c/bcs "y")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp "z")      [(c/mk c/bcs "z")]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/rt)     [(c/mk c/bcs c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
