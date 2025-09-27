;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lctl.edn")

(def hc-split-left           ["jump_view_left"])
(def mc-prev-split           ["PreviousSplit"])
(def hc-split-right          ["jump_view_right"])
(def mc-next-split           ["NextSplit"])
(def hc-split-up             ["jump_view_up"])
(def hc-split-down           ["jump_view_down"])
(def hc-jump                 ["goto_word"])
(def mc-jump                 ["JumpLine"])

(defn lctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "jump split left",    :exec hc-split-left}
                    {:program c/mc,    :action "jump split left",    :exec mc-prev-split}]}        [(c/mk c/btp c/_al) [(c/mk c/bt c/_al)] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump split right",   :exec hc-split-right}
                    {:program c/mc,    :action "jump split right",   :exec mc-next-split}]}        [(c/mk c/btp c/_ar) [(c/mk c/bt c/_ar)] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump split up",      :exec hc-split-up}
                    {:program c/mc,    :action "jump split up",      :exec mc-prev-split}]}        [(c/mk c/btp c/_au) [(c/mk c/bt c/_au)] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump split down",    :exec hc-split-down}
                    {:program c/mc,    :action "jump split down",    :exec mc-next-split}]}        [(c/mk c/btp c/_ad) [(c/mk c/bt c/_ad)] :term]

    ^{:doc/actions [{}]} [(c/mk c/btsp c/_al)     [(c/mk c/bts c/_al)]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_ar)     [(c/mk c/bts c/_ar)]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_au)     [(c/mk c/bts c/_au)]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_ad)     [(c/mk c/bts c/_ad)]]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`[[`"}]}           [(c/mk c/btp c/_ob)  [c/k_ob c/k_ob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`]]`"}]}           [(c/mk c/btp c/_cb)  [c/k_cb c/k_cb]]
    ^{:doc/actions [{}]} [(c/mk c/btp c/_sc)      [(c/mk c/bt c/_sc)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`'''`"}]}          [(c/mk c/btp c/_qu)  [c/k_qu c/k_qu c/k_qu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` || `"}]}         [(c/mk c/btp c/_bl)  [c/k_sp c/k_bl c/k_bl c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` << `"}]}         [(c/mk c/btp c/_cm)  [c/k_sp c/ks_cm c/ks_cm c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >> `"}]}         [(c/mk c/btp c/_pe)  [c/k_sp c/ks_pe c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`/// `"}]}         [(c/mk c/btp c/_sl)  [c/k_sl c/k_sl c/k_sl c/k_sp]]

    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`{{`"}]}           [(c/mk c/btsp c/_ob) [c/ks_ob c/ks_ob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`}}`"}]}           [(c/mk c/btsp c/_cb) [c/ks_cb c/ks_cb]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_sc)     [(c/mk c/bts c/_sc)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`\"\"\"`"}]}       [(c/mk c/btsp c/_qu) [c/ks_qu c/ks_qu c/ks_qu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .|| `"}]}        [(c/mk c/btsp c/_bl) [c/k_sp c/k_pe c/ks_bl c/ks_bl c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` <<< `"}]}        [(c/mk c/btsp c/_cm) [c/k_sp c/ks_cm c/ks_cm c/ks_cm c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >>> `"}]}        [(c/mk c/btsp c/_pe) [c/k_sp c/ks_pe c/ks_pe c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` /// `"}]}        [(c/mk c/btsp c/_sl) [c/k_sl c/k_sl c/k_sl c/k_sp]]

    ; action glyphs
    ^{:doc/actions [{}]} [(c/mk c/btp c/_db)      [(c/mk c/bt c/_db)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` == `"}]}         [(c/mk c/btp c/_re)  [c/k_sp c/k_eq c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` => `"}]}         [(c/mk c/btp c/_rs)  [c/k_sp c/k_eq c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ** `"}]}         [(c/mk c/btp c/_ro)  [c/k_sp c/ks_8 c/ks_8 c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` // `"}]}         [(c/mk c/btp c/_rc)  [c/k_sp c/k_sl c/k_sl c/k_sp]]
    ^{:doc/actions [{:program c/hc     :action "jumper"              :exec hc-jump}
                    {:program c/mc     :action "jumper"              :exec mc-jump}]}              [(c/mk c/btp c/_sp)  [(c/mk c/bt c/_sp)]]

    ^{:doc/actions [{}]} [(c/mk c/btsp c/_db)     [(c/mk c/bts c/_db)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .== `"}]}        [(c/mk c/btsp c/_re) [c/k_sp c/k_pe c/k_eq c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .=> `"}]}        [(c/mk c/btsp c/_rs) [c/k_sp c/k_pe c/k_eq c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .** `"}]}        [(c/mk c/btsp c/_ro) [c/k_sp c/k_pe c/ks_8 c/ks_8 c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .// `"}]}        [(c/mk c/btsp c/_rc) [c/k_sp c/k_pe c/k_sl c/k_sl c/k_sp]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_sp)     [(c/mk c/bts c/_sp)]]

    ; numeric glyphs
    ^{:doc/actions [{}]} [(c/mk c/btp c/_1)      [(c/mk c/bt c/_1)]]
    ^{:doc/actions [{}]} [(c/mk c/btp c/_2)      [(c/mk c/bt c/_2)]]
    ^{:doc/actions [{}]} [(c/mk c/btp c/_3)      [(c/mk c/bt c/_3)]]
    ^{:doc/actions [{}]} [(c/mk c/btp c/_4)      [(c/mk c/bt c/_4)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` %% `"}]}         [(c/mk c/btp c/_5)   [c/k_sp c/ks_5 c/ks_5 c/k_sp]]
    ^{:doc/actions [{}]} [(c/mk c/btp c/_6)      [(c/mk c/bt c/_6)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` && `"}]}         [(c/mk c/btp c/_7)   [c/k_sp c/ks_7 c/ks_7 c/k_sp]]
    ^{:doc/actions [{}]} [(c/mk c/btp c/_8)      [(c/mk c/bt c/_8)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`((`"}]}           [(c/mk c/btp c/_9)   [c/ks_9 c/ks_9]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`))`"}]}           [(c/mk c/btp c/_0)   [c/ks_0 c/ks_0]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` -- `"}]}         [(c/mk c/btp c/_hy)  [c/k_sp c/k_hy c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ++ `"}]}         [(c/mk c/btp c/_eq)  [c/k_sp c/ks_eq c/ks_eq c/k_sp]]

    ^{:doc/actions [{}]} [(c/mk c/btsp c/_1)     [(c/mk c/bts "1")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_2)     [(c/mk c/bts "2")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_3)     [(c/mk c/bts "3")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_4)     [(c/mk c/bts "4")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .%% `"}]}        [(c/mk c/btsp c/_5)  [c/k_sp c/k_pe c/ks_5 c/ks_5 c/k_sp]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_6)     [(c/mk c/bts "6")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .&& `"}]}        [(c/mk c/btsp c/_7)  [c/k_sp c/k_pe c/ks_7 c/ks_7 c/k_sp]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_8)     [(c/mk c/bts "8")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_9)     [(c/mk c/bts "9")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_0)     [(c/mk c/bts "0")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .-- `"}]}        [(c/mk c/btsp c/_hy) [c/k_sp c/k_pe c/k_hy c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .++ `"}]}        [(c/mk c/btsp c/_eq) [c/k_sp c/k_pe (c/mk c/bs c/_eq) (c/mk c/bs c/_eq) c/k_sp]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]} [(c/mk c/btp "a")       [(c/mk c/bt "a")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "b")       [(c/mk c/bt "b")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "c")       [(c/mk c/bt "c")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "d")       [(c/mk c/bt "d")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "e")       [(c/mk c/bt "e")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "f")       [(c/mk c/bt "f")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "g")       [(c/mk c/bt "g")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "h")       [(c/mk c/bt "h")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "i")       [(c/mk c/bt "i")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "j")       [(c/mk c/bt "j")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "k")       [(c/mk c/bt "k")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "l")       [(c/mk c/bt "l")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "m")       [(c/mk c/bt "m")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "n")       [(c/mk c/bt "n")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "o")       [(c/mk c/bt "o")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "p")       [(c/mk c/bt "p")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "q")       [(c/mk c/bt "q")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "r")       [(c/mk c/bt "r")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "s")       [(c/mk c/bt "s")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "t")       [(c/mk c/bt "t")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "u")       [(c/mk c/bt "u")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "v")       [(c/mk c/bt "v")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "w")       [(c/mk c/bt "w")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "x")       [(c/mk c/bt "x")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "y")       [(c/mk c/bt "y")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "z")       [(c/mk c/bt "z")]]
    ^{:doc/actions [{}]} [(c/mk c/btp c/_rt)      [(c/mk c/bt c/_rt)]]

    ^{:doc/actions [{}]} [(c/mk c/btsp "a")      [(c/mk c/bts "a")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "b")      [(c/mk c/bts "b")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "c")      [(c/mk c/bts "c")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "d")      [(c/mk c/bts "d")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "e")      [(c/mk c/bts "e")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "f")      [(c/mk c/bts "f")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "g")      [(c/mk c/bts "g")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "h")      [(c/mk c/bts "h")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "i")      [(c/mk c/bts "i")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "j")      [(c/mk c/bts "j")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "k")      [(c/mk c/bts "k")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "l")      [(c/mk c/bts "l")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "m")      [(c/mk c/bts "m")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "n")      [(c/mk c/bts "n")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "o")      [(c/mk c/bts "o")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "p")      [(c/mk c/bts "p")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "q")      [(c/mk c/bts "q")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "r")      [(c/mk c/bts "r")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "s")      [(c/mk c/bts "s")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "t")      [(c/mk c/bts "t")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "u")      [(c/mk c/bts "u")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "v")      [(c/mk c/bts "v")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "w")      [(c/mk c/bts "w")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "x")      [(c/mk c/bts "x")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "y")      [(c/mk c/bts "y")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "z")      [(c/mk c/bts "z")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/_rt)     [(c/mk c/bts c/_rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
