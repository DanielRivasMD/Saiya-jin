;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; JOKER
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.joker
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
            [config.function :as f]
))

(def out-file "joker.edn")

(defn joker []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Joker Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/sys,   :action "left space"}]}                                     [r/kotcp_al    [r/kotc_al]]
    ^{:doc/actions [{:program c/sys,   :action "right space"}]}                                    [r/kotcp_ar    [r/kotc_ar]]
    ^{:doc/actions [{:program c/sys,   :action "focus dock"}]}                                     [r/kotcp_au    [r/kotc_au]]
    ^{:doc/actions [{:program c/sys,   :action "focus menu"}]}                                     [r/kotcp_ad    [r/kotc_ad]]

    ; technical glyphs
    ^{:doc/actions [{:program c/rec,   :action "top center sixth"}]}                               [t/kotcp_ob    [t/kotc_ob]]
    ^{:doc/actions [{:program c/rec,   :action "top right sixth"}]}                                [t/kotcp_cb    [t/kotc_cb]]
    ^{:doc/actions [{:program c/rec,   :action "bottom left sixth"}]}                              [t/kotcp_sc    [t/kotc_sc]]
    ^{:doc/actions [{:program c/rec,   :action "bottom center sixth"}]}                            [t/kotcp_qu    [t/kotc_qu]]
    ^{:doc/actions [{:program c/rec,   :action "bottom right sixth"}]}                             [t/kotcp_bl    [t/kotc_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_cm    [t/kotc_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_pe    [t/kotc_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_sl    [t/kotc_sl]]

   ; action glyphs
    ^{:doc/actions [{:program c/sys,   :action "control center"}]}                                 [a/kotcp_db    [f/kt_f13]]
    ^{:doc/actions [{:program c/sys,   :action "speak"}]}                                          [a/kotcp_re    [f/kt_f14]]
    ^{:doc/actions [{:program c/sys,   :action "input keyboard"}]}                                 [a/kotcp_rs    [f/kt_f15]]
    ; ^{:doc/actions [{:program c/alf,   :action "file prompt"}]}                                    [a/kotcp_ro    [c/kocs_lock]]
    ; ^{:doc/actions [{:program c/alf,   :action "prompt"}]}                                         [a/kotcp_rc    [c/koc_lock]]
    ^{:doc/actions [{:program c/sys,   :action "listen"}]}                                         [a/kotcp_sp    [f/kt_f16]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/kotcp_1     [n/kotc_1]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_2     [n/kotc_2]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_3     [n/kotc_3]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_4     [n/kotc_4]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_5     [n/kotc_5]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_6     [n/kotc_6]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_7     [n/kotc_7]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_8     [n/kotc_8]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_9     [n/kotc_9]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_0     [n/kotc_0]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_hy    [n/kotc_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_eq    [n/kotc_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{:program c/sys,   :action "app windows"}]}                                    [b/kotcp_a     [b/kotc_a]]
    ^{:doc/actions [{:program c/rec,   :action "last third"}]}                                     [b/kotcp_b     [b/kotc_b]]
    ^{:doc/actions [{:program c/rec,   :action "first third"}]}                                    [b/kotcp_c     [b/kotc_c]]
    ^{:doc/actions [{:program c/sys,   :action "show desktop"}]}                                   [b/kotcp_d     [b/kotc_d]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_e     [b/kotc_e]]
    ^{:doc/actions [{:program c/rec,   :action "maximize"}]}                                       [b/kotcp_f     [b/kotc_f]]
    ^{:doc/actions [{:program c/rec,   :action "center"}]}                                         [b/kotcp_g     [b/kotc_g]]
    ^{:doc/actions [{:program c/rec,   :action "left half"}]}                                      [b/kotcp_h     [b/kotc_h]]
    ^{:doc/actions [{:program c/rec,   :action "bottom right"}]}                                   [b/kotcp_i     [b/kotc_i]]
    ^{:doc/actions [{:program c/rec,   :action "bottom half"}]}                                    [b/kotcp_j     [b/kotc_j]]
    ^{:doc/actions [{:program c/rec,   :action "top half"}]}                                       [b/kotcp_k     [b/kotc_k]]
    ^{:doc/actions [{:program c/rec,   :action "right half"}]}                                     [b/kotcp_l     [b/kotc_l]]
    ^{:doc/actions [{:program c/sys,   :action "merge windows"}]}                                  [b/kotcp_m     [b/kotc_m]]
    ^{:doc/actions [{:program c/rec,   :action "last two thirds"}]}                                [b/kotcp_n     [b/kotc_n]]
    ^{:doc/actions [{:program c/rec,   :action "top right"}]}                                      [b/kotcp_o     [b/kotc_o]]
    ^{:doc/actions [{:program c/rec,   :action "top left sixth"}]}                                 [b/kotcp_p     [b/kotc_p]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_q     [b/kotc_q]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_r     [b/kotc_r]]
    ^{:doc/actions [{:program c/sys,   :action "mission control"}]}                                [b/kotcp_s     [b/kotc_s]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_t     [b/kotc_t]]
    ^{:doc/actions [{:program c/rec,   :action "bottom left"}]}                                    [b/kotcp_u     [b/kotc_u]]
    ^{:doc/actions [{:program c/rec,   :action "center third"}]}                                   [b/kotcp_v     [b/kotc_v]]
    ^{:doc/actions [{:program c/sys,   :action "take screenshot"}]}                                [b/kotcp_w     [b/kotc_w]]
    ^{:doc/actions [{:program c/rec,   :action "first two thirds"}]}                               [b/kotcp_x     [b/kotc_x]]
    ^{:doc/actions [{:program c/rec,   :action "top left"}]}                                       [b/kotcp_y     [b/kotc_y]]
    ^{:doc/actions [{:program c/rec    :action "restore"}]}                                        [b/kotcp_z     [b/kotc_z]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_rt    [b/kotcp_rt]]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (joker)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
