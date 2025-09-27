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
                    {:program c/mc,    :action "jump split left",    :exec mc-prev-split}]}        [(c/mk c/btp c/al) [(c/mk c/bt c/al)] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump split right",   :exec hc-split-right}
                    {:program c/mc,    :action "jump split right",   :exec mc-next-split}]}        [(c/mk c/btp c/ar) [(c/mk c/bt c/ar)] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump split up",      :exec hc-split-up}
                    {:program c/mc,    :action "jump split up",      :exec mc-prev-split}]}        [(c/mk c/btp c/au) [(c/mk c/bt c/au)] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump split down",    :exec hc-split-down}
                    {:program c/mc,    :action "jump split down",    :exec mc-next-split}]}        [(c/mk c/btp c/ad) [(c/mk c/bt c/ad)] :term]

    ^{:doc/actions [{}]} [(c/mk c/btsp c/al)     [(c/mk c/bts c/al)]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/ar)     [(c/mk c/bts c/ar)]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/au)     [(c/mk c/bts c/au)]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/ad)     [(c/mk c/bts c/ad)]]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`[[`"}]}           [(c/mk c/btp c/ob) [c/kob c/kob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`]]`"}]}           [(c/mk c/btp c/cb) [c/kcb c/kcb]]
    ^{:doc/actions [{}]} [(c/mk c/btp c/sc)      [(c/mk c/bt c/sc)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`'''`"}]}          [(c/mk c/btp c/qu) [c/kqu c/kqu c/kqu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` || `"}]}         [(c/mk c/btp c/bl) [c/ksp c/kbl c/kbl c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` << `"}]}         [(c/mk c/btp c/cm) [c/ksp c/kscm c/kscm c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >> `"}]}         [(c/mk c/btp c/pe) [c/ksp c/kspe c/kspe c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`/// `"}]}         [(c/mk c/btp c/sl) [c/ksl c/ksl c/ksl c/ksp]]

    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`{{`"}]}           [(c/mk c/btsp c/ob) [c/ksob c/ksob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`}}`"}]}           [(c/mk c/btsp c/cb) [c/kscb c/kscb]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/sc)     [(c/mk c/bts c/sc)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`\"\"\"`"}]}       [(c/mk c/btsp c/qu) [c/ksqu c/ksqu c/ksqu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .|| `"}]}        [(c/mk c/btsp c/bl) [c/ksp c/kpe c/ksbl c/ksbl c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` <<< `"}]}        [(c/mk c/btsp c/cm) [c/ksp c/kscm c/kscm c/kscm c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >>> `"}]}        [(c/mk c/btsp c/pe) [c/ksp c/kspe c/kspe c/kspe c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` /// `"}]}        [(c/mk c/btsp c/sl) [c/ksl c/ksl c/ksl c/ksp]]

    ; action glyphs
    ^{:doc/actions [{}]} [(c/mk c/btp c/db)      [(c/mk c/bt c/db)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` == `"}]}         [(c/mk c/btp c/re) [c/ksp c/keq c/keq c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` => `"}]}         [(c/mk c/btp c/rs) [c/ksp c/keq c/kspe c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ** `"}]}         [(c/mk c/btp c/ro) [c/ksp (c/mk c/bs "8") (c/mk c/bs "8") c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` // `"}]}         [(c/mk c/btp c/rc) [c/ksp c/ksl c/ksl c/ksp]]
    ^{:doc/actions [{:program c/hc     :action "jumper"              :exec hc-jump}
                    {:program c/mc     :action "jumper"              :exec mc-jump}]}              [(c/mk c/btp c/sp) [(c/mk c/bt c/sp)]]

    ^{:doc/actions [{}]} [(c/mk c/btsp c/db)     [(c/mk c/bts c/db)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .== `"}]}        [(c/mk c/btsp c/re) [c/ksp c/kpe c/keq c/keq c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .=> `"}]}        [(c/mk c/btsp c/rs) [c/ksp c/kpe c/keq c/kspe c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .** `"}]}        [(c/mk c/btsp c/ro) [c/ksp c/kpe (c/mk c/bs "8") (c/mk c/bs "8") c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .// `"}]}        [(c/mk c/btsp c/rc) [c/ksp c/kpe c/ksl c/ksl c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/btsp c/sp)     [(c/mk c/bts c/sp)]]

    ; numeric glyphs
    ^{:doc/actions [{}]} [(c/mk c/btp "1")       [(c/mk c/bt "1")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "2")       [(c/mk c/bt "2")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "3")       [(c/mk c/bt "3")]]
    ^{:doc/actions [{}]} [(c/mk c/btp "4")       [(c/mk c/bt "4")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` %% `"}]}         [(c/mk c/btp "5") [c/ksp (c/mk c/bs "5") (c/mk c/bs "5") c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/btp "6")       [(c/mk c/bt "6")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` && `"}]}         [(c/mk c/btp "7") [c/ksp (c/mk c/bs "7") (c/mk c/bs "7") c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/btp "8")       [(c/mk c/bt "8")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`((`"}]}           [(c/mk c/btp "9") [(c/mk c/bs "9") (c/mk c/bs "9")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`))`"}]}           [(c/mk c/btp "0") [(c/mk c/bs "0") (c/mk c/bs "0")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` -- `"}]}         [(c/mk c/btp c/hy) [c/ksp c/khy c/khy c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ++ `"}]}         [(c/mk c/btp c/eq) [c/ksp c/kseq c/kseq c/ksp]]

    ^{:doc/actions [{}]} [(c/mk c/btsp "1")      [(c/mk c/bts "1")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "2")      [(c/mk c/bts "2")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "3")      [(c/mk c/bts "3")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "4")      [(c/mk c/bts "4")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .%% `"}]}        [(c/mk c/btsp "5") [c/ksp c/pe (c/mk c/bts "5") (c/mk c/bts "5") c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "6")      [(c/mk c/bts "6")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .&& `"}]}        [(c/mk c/btsp "7") [c/ksp c/pe (c/mk c/bts "7") (c/mk c/bts "7") c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "8")      [(c/mk c/bts "8")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "9")      [(c/mk c/bts "9")]]
    ^{:doc/actions [{}]} [(c/mk c/btsp "0")      [(c/mk c/bts "0")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .-- `"}]}        [(c/mk c/btsp c/hy) [c/ksp c/pe c/hy c/hy c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .++ `"}]}        [(c/mk c/btsp c/eq) [c/ksp c/pe (c/mk c/bts c/eq) (c/mk c/bts c/eq) c/ksp]]

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
    ^{:doc/actions [{}]} [(c/mk c/btp c/rt)      [(c/mk c/bt c/rt)]]

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
    ^{:doc/actions [{}]} [(c/mk c/btsp c/rt)     [(c/mk c/bts c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
