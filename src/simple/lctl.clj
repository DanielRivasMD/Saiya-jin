;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lctl.edn")

(defn lctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "jump split left",    :exec ["jump_view_left"]}
                    {:program c/mc,    :action "jump split left",    :exec ["PreviousSplit"]}]}    [(c/mk c/ilctl c/al) [(c/mk c/olctl c/al)] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump split right",   :exec ["jump_view_right"]}
                    {:program c/mc,    :action "jump split right",   :exec ["NextSplit"]}]}        [(c/mk c/ilctl c/ar) [(c/mk c/olctl c/ar)] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump split up",      :exec ["jump_view_up"]}
                    {:program c/mc,    :action "jump split up",      :exec ["PreviousSplit"]}]}    [(c/mk c/ilctl c/au) [(c/mk c/olctl c/au)] :term]
    ^{:doc/actions [{:program c/hc,    :action "jump split down",    :exec ["jump_view_down"]}
                    {:program c/mc,    :action "jump split down",    :exec ["NextSplit"]}]}        [(c/mk c/ilctl c/ad) [(c/mk c/olctl c/ad)] :term]

    ^{:doc/actions [{}]} [(c/mk c/ilctls c/al)   [(c/mk c/olctls c/al)]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls c/ar)   [(c/mk c/olctls c/ar)]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls c/au)   [(c/mk c/olctls c/au)]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls c/ad)   [(c/mk c/olctls c/ad)]]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`[[`"}]}           [(c/mk c/ilctl c/ob) [c/kob c/kob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`]]`"}]}           [(c/mk c/ilctl c/cb) [c/kcb c/kcb]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl c/sc)    [(c/mk c/olctl c/sc)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`'''`"}]}          [(c/mk c/ilctl c/qu) [c/kqu c/kqu c/kqu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` || `"}]}         [(c/mk c/ilctl c/bl) [c/ksp c/kbl c/kbl c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` << `"}]}         [(c/mk c/ilctl c/cm) [c/ksp c/kscm c/kscm c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >> `"}]}         [(c/mk c/ilctl c/pe) [c/ksp c/kspe c/kspe c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`/// `"}]}         [(c/mk c/ilctl c/sl) [c/ksl c/ksl c/ksl c/ksp]]

    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`{{`"}]}           [(c/mk c/ilctls c/ob) [c/ksob c/ksob]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`}}`"}]}           [(c/mk c/ilctls c/cb) [c/kscb c/kscb]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls c/sc)   [(c/mk c/olctls c/sc)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`\"\"\"`"}]}       [(c/mk c/ilctls c/qu) [c/ksqu c/ksqu c/ksqu]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .|| `"}]}        [(c/mk c/ilctls c/bl) [c/ksp c/kpe c/ksbl c/ksbl c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` <<< `"}]}        [(c/mk c/ilctls c/cm) [c/ksp c/kscm c/kscm c/kscm c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` >>> `"}]}        [(c/mk c/ilctls c/pe) [c/ksp c/kspe c/kspe c/kspe c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` /// `"}]}        [(c/mk c/ilctls c/sl) [c/ksl c/ksl c/ksl c/ksp]]

    ; action glyphs
    ^{:doc/actions [{}]} [(c/mk c/ilctl c/db)    [(c/mk c/ilctl c/db)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` == `"}]}         [(c/mk c/ilctl c/re) [c/ksp c/keq c/keq c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` => `"}]}         [(c/mk c/ilctl c/rs) [c/ksp c/keq c/kspe c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ** `"}]}         [(c/mk c/ilctl c/ro) [c/ksp (c/mk c/bs "8") (c/mk c/bs "8") c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` // `"}]}         [(c/mk c/ilctl c/rc) [c/ksp c/ksl c/ksl c/ksp]]
    ^{:doc/actions [{:program c/hc     :action "jumper"              :exec ["goto_word"]}
                    {:program c/mc     :action "jumper"              :exec ["JumpLine"]}]}         [(c/mk c/ilctl c/sp) [(c/mk c/olctl c/sp)]]

    ^{:doc/actions [{}]} [(c/mk c/ilctls c/db)   [(c/mk c/ilctls c/db)]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .== `"}]}        [(c/mk c/ilctls c/re) [c/ksp c/kpe c/keq c/keq c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .=> `"}]}        [(c/mk c/ilctls c/rs) [c/ksp c/kpe c/keq c/kspe c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .** `"}]}        [(c/mk c/ilctls c/ro) [c/ksp c/kpe (c/mk c/bs "8") (c/mk c/bs "8") c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .// `"}]}        [(c/mk c/ilctls c/rc) [c/ksp c/kpe c/ksl c/ksl c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls c/sp)   [(c/mk c/ilctls c/sp)]]

    ; numeric glyphs
    ^{:doc/actions [{}]} [(c/mk c/ilctl "1")     [(c/mk c/ilctl "1")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "2")     [(c/mk c/ilctl "2")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "3")     [(c/mk c/ilctl "3")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "4")     [(c/mk c/ilctl "4")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` %% `"}]}         [(c/mk c/ilctl "5") [c/ksp (c/mk c/bs "5") (c/mk c/bs "5") c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "6")     [(c/mk c/ilctl "6")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` && `"}]}         [(c/mk c/ilctl "7") [c/ksp (c/mk c/bs "7") (c/mk c/bs "7") c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "8")     [(c/mk c/ilctl "8")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`((`"}]}           [(c/mk c/ilctl "9") [(c/mk c/bs "9") (c/mk c/bs "9")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "`))`"}]}           [(c/mk c/ilctl "0") [(c/mk c/bs "0") (c/mk c/bs "0")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` -- `"}]}         [(c/mk c/ilctl c/hy) [c/ksp c/khy c/khy c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` ++ `"}]}         [(c/mk c/ilctl c/eq) [c/ksp c/kseq c/kseq c/ksp]]

    ^{:doc/actions [{}]} [(c/mk c/ilctls "1")    [(c/mk c/ilctls "1")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "2")    [(c/mk c/ilctls "2")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "3")    [(c/mk c/ilctls "3")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "4")    [(c/mk c/ilctls "4")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .%% `"}]}        [(c/mk c/ilctls "5") [c/ksp c/pe (c/mk c/olctls "5") (c/mk c/olctls "5") c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "6")    [(c/mk c/ilctls "6")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .&& `"}]}        [(c/mk c/ilctls "7") [c/ksp c/pe (c/mk c/olctls "7") (c/mk c/olctls "7") c/ksp]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "8")    [(c/mk c/ilctls "8")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "9")    [(c/mk c/ilctls "9")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "0")    [(c/mk c/ilctls "0")]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .-- `"}]}        [(c/mk c/ilctls c/hy) [c/ksp c/pe c/hy c/hy c/ksp]]
    ^{:doc/actions [{:program c/tm     :action "terminal"            :sequence "` .++ `"}]}        [(c/mk c/ilctls c/eq) [c/ksp c/pe (c/mk c/olctls c/eq) (c/mk c/olctls c/eq) c/ksp]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]} [(c/mk c/ilctl "a")     [(c/mk c/olctl "a")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "b")     [(c/mk c/olctl "b")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "c")     [(c/mk c/olctl "c")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "d")     [(c/mk c/olctl "d")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "e")     [(c/mk c/olctl "e")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "f")     [(c/mk c/olctl "f")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "g")     [(c/mk c/olctl "g")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "h")     [(c/mk c/olctl "h")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "i")     [(c/mk c/olctl "i")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "j")     [(c/mk c/olctl "j")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "k")     [(c/mk c/olctl "k")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "l")     [(c/mk c/olctl "l")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "m")     [(c/mk c/olctl "m")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "n")     [(c/mk c/olctl "n")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "o")     [(c/mk c/olctl "o")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "p")     [(c/mk c/olctl "p")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "q")     [(c/mk c/olctl "q")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "r")     [(c/mk c/olctl "r")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "s")     [(c/mk c/olctl "s")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "t")     [(c/mk c/olctl "t")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "u")     [(c/mk c/olctl "u")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "v")     [(c/mk c/olctl "v")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "w")     [(c/mk c/olctl "w")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "x")     [(c/mk c/olctl "x")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "y")     [(c/mk c/olctl "y")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl "z")     [(c/mk c/olctl "z")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctl c/rt)    [(c/mk c/olctl c/rt)]]

    ^{:doc/actions [{}]} [(c/mk c/ilctls "a")    [(c/mk c/olctls "a")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "b")    [(c/mk c/olctls "b")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "c")    [(c/mk c/olctls "c")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "d")    [(c/mk c/olctls "d")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "e")    [(c/mk c/olctls "e")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "f")    [(c/mk c/olctls "f")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "g")    [(c/mk c/olctls "g")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "h")    [(c/mk c/olctls "h")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "i")    [(c/mk c/olctls "i")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "j")    [(c/mk c/olctls "j")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "k")    [(c/mk c/olctls "k")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "l")    [(c/mk c/olctls "l")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "m")    [(c/mk c/olctls "m")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "n")    [(c/mk c/olctls "n")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "o")    [(c/mk c/olctls "o")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "p")    [(c/mk c/olctls "p")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "q")    [(c/mk c/olctls "q")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "r")    [(c/mk c/olctls "r")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "s")    [(c/mk c/olctls "s")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "t")    [(c/mk c/olctls "t")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "u")    [(c/mk c/olctls "u")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "v")    [(c/mk c/olctls "v")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "w")    [(c/mk c/olctls "w")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "x")    [(c/mk c/olctls "x")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "y")    [(c/mk c/olctls "y")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls "z")    [(c/mk c/olctls "z")]]
    ^{:doc/actions [{}]} [(c/mk c/ilctls c/rt)   [(c/mk c/olctls c/rt)]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
