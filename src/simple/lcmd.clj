;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lcmd.edn")

(def hc-line-start ["goto_line_start"])
(def mc-line-start ["StartOfLine"])
(def hc-line-end ["goto_line_end", "move_char_right"])
(def mc-line-end ["EndOfLine"])
(def hc-file-start ["goto_file_start"])
(def lg-top ["gotoTop"])
(def mc-file-start ["CursorStart"])
(def ze-page-up ["PageScrollUp"])
(def hc-file-end ["goto_last_line"])
(def lg-bottom ["gotoBottom"])
(def mc-file-end ["CursorEnd"])
(def ze-page-down ["PageScrollDown"])

(def hc-select-line-start ["select_mode", "goto_line_start", "MODE"])
(def mc-select-line-start ["SelectToStartOfLine"])
(def hc-select-line-end ["select_mode", "goto_line_end", "MODE"])
(def mc-select-line-end ["SelectToEndOfLine"])
(def hc-select-file-start ["extend_to_file_start"])
(def mc-select-file-start ["SelectToStart"])
(def hc-select-file-end ["extend_to_file_end"])
(def mc-select-file-end ["SelectToEnd"])

(defn lcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto line start",    :exec hc-line-start}
                    {:program c/mc,    :action "goto line start",    :exec mc-line-start}]}        [(c/mk c/ilcmd c/al)  [:!Ta]  :term]
    ^{:doc/actions [{:program c/hc,    :action "goto line end",      :exec hc-line-end}
                    {:program c/mc,    :action "goto line end",      :exec mc-line-end}]}          [(c/mk c/ilcmd c/ar)  [:!Te]  :term]
    ^{:doc/actions [{:program c/hc,    :action "goto file start",    :exec hc-file-start}
                    {:program c/lg,    :action "goto top",           :exec lg-top}
                    {:program c/mc,    :action "goto file start",    :exec mc-file-start}
                    {:program c/ze,    :action "page up",            :exec ze-page-up}]}           [(c/mk c/ilcmd c/au)  [c/khm] :term]
    ^{:doc/actions [{:program c/hc,    :action "goto file end",      :exec hc-file-end}
                    {:program c/lg,    :action "goto bottom",        :exec lg-bottom}
                    {:program c/mc,    :action "goto file end",      :exec mc-file-end}
                    {:program c/ze,    :action "page down",          :exec ze-page-down}]}         [(c/mk c/ilcmd c/ad)  [c/ked]  :term]

    ^{:doc/actions [{:program c/hc,    :action "select line start",  :exec hc-select-line-start}
                    {:program c/mc,    :action "select line start",  :exec mc-select-line-start}]} [(c/mk c/ilcmds c/al) [c/koal]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select line end",    :exec hc-select-line-end}
                    {:program c/mc,    :action "select line end",    :exec mc-select-line-end}]}   [(c/mk c/ilcmds c/ar) [c/koar] :term]
    ^{:doc/actions [{:program c/hc,    :action "select file start",  :exec hc-select-file-start}
                    {:program c/mc,    :action "select file start",  :exec mc-select-file-start}]} [(c/mk c/ilcmds c/au) [c/koau]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select file end",    :exec hc-select-file-end}
                    {:program c/mc,    :action "select file end",    :exec mc-select-file-end}]}   [(c/mk c/ilcmds c/ad) [c/koad]  :term]

    ; technical glyphs
    ^{:doc/actions [{}]} [(c/mk c/ilcmd c/ob)    [(c/mk c/olcmd c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd c/cb)    [(c/mk c/olcmd c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd c/sc)    [(c/mk c/olcmd c/sc)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`::<`"}]}          [(c/mk c/ilcmd c/qu)  [:!Ssemicolon :!Ssemicolon :!Scomma]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd c/bl)    [(c/mk c/olcmd c/bl)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "' <- '"}]}         [(c/mk c/ilcmd c/cm)  [:spacebar :!Scomma :hyphen :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` -> `"}]}         [(c/mk c/ilcmd c/pe)  [:spacebar :hyphen :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`//! `"}]}         [(c/mk c/ilcmd c/sl)  [:slash :slash :!S1 :spacebar]]

    ^{:doc/actions [{}]} [(c/mk c/ilcmds c/ob)   [(c/mk c/olcmds c/ob)]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds c/cb)   [(c/mk c/olcmds c/cb)]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds c/sc)   [(c/mk c/olcmds c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds c/qu)   [(c/mk c/olcmds c/qu)]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds c/bl)   [(c/mk c/olcmds c/bl)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` <<- `"}]}        [(c/mk c/ilcmds c/cm) [:spacebar :!Scomma :!Scomma :hyphen :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ->> `"}]}        [(c/mk c/ilcmds c/pe) [:spacebar :hyphen :!Speriod :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`#!/`"}]}          [(c/mk c/ilcmds c/sl) [:!S3 :!S1 :slash]]

    ; action glyphs
    ^{:doc/actions [{}]} [(c/mk c/ilcmd c/db)    [(c/mk c/olcmd c/db)]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd c/re)    [(c/mk c/olcmd c/re)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` != `"}]}        [(c/mk c/ilcmd c/rs)  [:spacebar :!S1 :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` *= `"}]}        [(c/mk c/ilcmd c/ro)  [:spacebar :!S8 :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` /= `"}]}        [(c/mk c/ilcmd c/rc)  [:spacebar :slash :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd c/sp)    [(c/mk c/olcmd c/sp)]]

    ^{:doc/actions [{}]} [(c/mk c/ilcmds c/db)   [(c/mk c/olcmds c/db)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "`; `"}]}          [(c/mk c/ilcmds c/re) [:spacebar :!Ssemicolon :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` .!= `"}]}       [(c/mk c/ilcmds c/rs) [:spacebar :period :!S1 :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` .*= `"}]}       [(c/mk c/ilcmds c/ro) [:spacebar :period :!S8 :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` ./= `"}]}       [(c/mk c/ilcmds c/rc) [:spacebar :period :slash :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds c/sp)   [(c/mk c/olcmds c/sp)]]

    ; numeric glyphs
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "1")     [(c/mk c/olcmd "1")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "2")     [(c/mk c/olcmd "2")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "3")     [(c/mk c/olcmd "3")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "4")     [(c/mk c/olcmd "4")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "5")     [(c/mk c/olcmd "5")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "6")     [(c/mk c/olcmd "6")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "7")     [(c/mk c/olcmd "7")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "8")     [(c/mk c/olcmd "8")]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence ".("}]}                [(c/mk c/ilcmd "9")   [:period :!S9]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "0")     [(c/mk c/olcmd "0")]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` -= `"}]}            [(c/mk c/ilcmd c/hy)  [:spacebar :hyphen :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` += `"}]}            [(c/mk c/ilcmd c/eq)  [:spacebar :!Sequal_sign :equal_sign :spacebar]]

    ^{:doc/actions [{}]} [(c/mk c/ilcmds "1")    [(c/mk c/olcmds "1")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "2")    [(c/mk c/olcmds "2")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "3")    [(c/mk c/olcmds "3")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "4")    [(c/mk c/olcmds "4")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "5")    [(c/mk c/olcmds "5")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "6")    [(c/mk c/olcmds "6")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "7")    [(c/mk c/olcmds "7")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "8")    [(c/mk c/olcmds "8")]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "`:(`"}]}              [(c/mk c/ilcmds "9")  [:!Ssemicolon :!S9]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "0")    [(c/mk c/olcmds "0")]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` .-= `"}]}           [(c/mk c/ilcmds c/hy) [:spacebar :period :hyphen :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` .+= `"}]}           [(c/mk c/ilcmds c/eq) [:spacebar :period :!Sequal_sign :equal_sign :spacebar]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "a")     [(c/mk c/olcmd "a")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "b")     [(c/mk c/olcmd "b")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "c")     [(c/mk c/olcmd "c")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "d")     [(c/mk c/olcmd "d")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "e")     [(c/mk c/olcmd "e")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "f")     [(c/mk c/olcmd "f")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "g")     [(c/mk c/olcmd "g")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "h")     [(c/mk c/olcmd "h")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "i")     [(c/mk c/olcmd "i")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "j")     [(c/mk c/olcmd "j")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "k")     [(c/mk c/olcmd "k")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "l")     [(c/mk c/olcmd "l")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "m")     [(c/mk c/olcmd "m")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "n")     [(c/mk c/olcmd "n")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "o")     [(c/mk c/olcmd "o")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "p")     [(c/mk c/olcmd "p")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "q")     [(c/mk c/olcmd "q")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "r")     [(c/mk c/olcmd "r")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "s")     [(c/mk c/olcmd "s")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "t")     [(c/mk c/olcmd "t")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "u")     [(c/mk c/olcmd "u")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "v")     [(c/mk c/olcmd "v")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "w")     [(c/mk c/olcmd "w")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "x")     [(c/mk c/olcmd "x")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "y")     [(c/mk c/olcmd "y")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd "z")     [(c/mk c/olcmd "z")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmd c/rt)    [(c/mk c/olcmd c/rt)]]

    ^{:doc/actions [{}]} [(c/mk c/ilcmds "a")    [(c/mk c/olcmds "a")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "b")    [(c/mk c/olcmds "b")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "c")    [(c/mk c/olcmds "c")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "d")    [(c/mk c/olcmds "d")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "e")    [(c/mk c/olcmds "e")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "f")    [(c/mk c/olcmds "f")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "g")    [(c/mk c/olcmds "g")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "h")    [(c/mk c/olcmds "h")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "i")    [(c/mk c/olcmds "i")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "j")    [(c/mk c/olcmds "j")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "k")    [(c/mk c/olcmds "k")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "l")    [(c/mk c/olcmds "l")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "m")    [(c/mk c/olcmds "m")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "n")    [(c/mk c/olcmds "n")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "o")    [(c/mk c/olcmds "o")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "p")    [(c/mk c/olcmds "p")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "q")    [(c/mk c/olcmds "q")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "r")    [(c/mk c/olcmds "r")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "s")    [(c/mk c/olcmds "s")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "t")    [(c/mk c/olcmds "t")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "u")    [(c/mk c/olcmds "u")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "v")    [(c/mk c/olcmds "v")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "w")    [(c/mk c/olcmds "w")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "x")    [(c/mk c/olcmds "x")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "y")    [(c/mk c/olcmds "y")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds "z")    [(c/mk c/olcmds "z")]]
    ^{:doc/actions [{}]} [(c/mk c/ilcmds c/rt)   [(c/mk c/olcmds c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
