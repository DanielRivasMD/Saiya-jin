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
                    {:program c/mc,    :action "goto line start",    :exec mc-line-start}]}        [(c/mk c/bcp c/_al)  [:!Ta]    :term]
    ^{:doc/actions [{:program c/hc,    :action "goto line end",      :exec hc-line-end}
                    {:program c/mc,    :action "goto line end",      :exec mc-line-end}]}          [(c/mk c/bcp c/_ar)  [:!Te]    :term]
    ^{:doc/actions [{:program c/hc,    :action "goto file start",    :exec hc-file-start}
                    {:program c/lg,    :action "goto top",           :exec lg-top}
                    {:program c/mc,    :action "goto file start",    :exec mc-file-start}
                    {:program c/ze,    :action "page up",            :exec ze-page-up}]}           [(c/mk c/bcp c/_au)  [c/k_hm]   :term]
    ^{:doc/actions [{:program c/hc,    :action "goto file end",      :exec hc-file-end}
                    {:program c/lg,    :action "goto bottom",        :exec lg-bottom}
                    {:program c/mc,    :action "goto file end",      :exec mc-file-end}
                    {:program c/ze,    :action "page down",          :exec ze-page-down}]}         [(c/mk c/bcp c/_ad)  [c/k_ed]   :term]

    ^{:doc/actions [{:program c/hc,    :action "select line start",  :exec hc-select-line-start}
                    {:program c/mc,    :action "select line start",  :exec mc-select-line-start}]} [(c/mk c/bcsp c/_al) [c/ko_al]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select line end",    :exec hc-select-line-end}
                    {:program c/mc,    :action "select line end",    :exec mc-select-line-end}]}   [(c/mk c/bcsp c/_ar) [c/ko_ar]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select file start",  :exec hc-select-file-start}
                    {:program c/mc,    :action "select file start",  :exec mc-select-file-start}]} [(c/mk c/bcsp c/_au) [c/ko_au]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select file end",    :exec hc-select-file-end}
                    {:program c/mc,    :action "select file end",    :exec mc-select-file-end}]}   [(c/mk c/bcsp c/_ad) [c/ko_ad]  :term]

    ; technical glyphs
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_ob)     [(c/mk c/bc c/_ob)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_cb)     [(c/mk c/bc c/_cb)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_sc)     [(c/mk c/bc c/_sc)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`::<`"}]}          [(c/mk c/bcp c/_qu)  [c/ks_sc c/ks_sc c/ks_cm]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_bl)     [(c/mk c/bc c/_bl)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "' <- '"}]}         [(c/mk c/bcp c/_cm)  [c/k_sp c/ks_cm c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` -> `"}]}         [(c/mk c/bcp c/_pe)  [c/k_sp c/k_hy c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`//! `"}]}         [(c/mk c/bcp c/_sl)  [c/k_sl c/k_sl c/ks_1 c/k_sp]]

    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_ob)    [(c/mk c/bcs c/_ob)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_cb)    [(c/mk c/bcs c/_cb)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_sc)    [(c/mk c/bcs c/_sc)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_qu)    [(c/mk c/bcs c/_qu)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_bl)    [(c/mk c/bcs c/_bl)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` <<- `"}]}        [(c/mk c/bcsp c/_cm) [c/k_sp c/ks_cm c/ks_cm c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ->> `"}]}        [(c/mk c/bcsp c/_pe) [c/k_sp c/k_hy c/ks_pe c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`#!/`"}]}          [(c/mk c/bcsp c/_sl) [c/ks_3 c/ks_1 c/k_sl]]

    ; action glyphs
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_db)     [(c/mk c/bc c/_db)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_re)     [(c/mk c/bc c/_re)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` != `"}]}        [(c/mk c/bcp c/_rs)  [c/k_sp c/ks_1 c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` *= `"}]}        [(c/mk c/bcp c/_ro)  [c/k_sp c/ks_8 c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` /= `"}]}        [(c/mk c/bcp c/_rc)  [c/k_sp c/k_sl c/k_eq c/k_sp]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_sp)     [(c/mk c/bc c/_sp)]]

    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_db)    [(c/mk c/bcs c/_db)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "`; `"}]}          [(c/mk c/bcsp c/_re) [c/k_sp c/ks_sc c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` .!= `"}]}       [(c/mk c/bcsp c/_rs) [c/k_sp c/k_pe c/ks_1 c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` .*= `"}]}       [(c/mk c/bcsp c/_ro) [c/k_sp c/k_pe c/ks_8 c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` ./= `"}]}       [(c/mk c/bcsp c/_rc) [c/k_sp c/k_pe c/k_sl c/k_eq c/k_sp]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_sp)    [(c/mk c/bcs c/_sp)]]

    ; numeric glyphs
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_1)      [(c/mk c/bc c/_1)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_2)      [(c/mk c/bc c/_2)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_3)      [(c/mk c/bc c/_3)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_4)      [(c/mk c/bc c/_4)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_5)      [(c/mk c/bc c/_5)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_6)      [(c/mk c/bc c/_6)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_7)      [(c/mk c/bc c/_7)]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_8)      [(c/mk c/bc c/_8)]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence ".("}]}                [(c/mk c/bcp c/_9)   [c/k_pe c/ks_9]]
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_0)      [(c/mk c/bc c/_0)]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` -= `"}]}            [(c/mk c/bcp c/_hy)  [c/k_sp c/k_hy c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` += `"}]}            [(c/mk c/bcp c/_eq)  [c/k_sp c/ks_eq c/k_eq c/k_sp]]

    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_1)     [(c/mk c/bcs c/_1)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_2)     [(c/mk c/bcs c/_2)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_3)     [(c/mk c/bcs c/_3)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_4)     [(c/mk c/bcs c/_4)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_5)     [(c/mk c/bcs c/_5)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_6)     [(c/mk c/bcs c/_6)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_7)     [(c/mk c/bcs c/_7)]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_8)     [(c/mk c/bcs c/_8)]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "`:(`"}]}              [(c/mk c/bcsp c/_9)  [c/ks_sc c/ks_9]]
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_0)     [(c/mk c/bcs c/_0)]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` .-= `"}]}           [(c/mk c/bcsp c/_hy) [c/k_sp c/k_pe c/k_hy c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` .+= `"}]}           [(c/mk c/bcsp c/_eq) [c/k_sp c/k_pe c/ks_eq c/k_eq c/k_sp]]

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
    ^{:doc/actions [{}]} [(c/mk c/bcp c/_rt)      [(c/mk c/bc c/_rt)]]

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
    ^{:doc/actions [{}]} [(c/mk c/bcsp c/_rt)     [(c/mk c/bcs c/_rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
