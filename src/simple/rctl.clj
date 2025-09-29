;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.rctl
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "rctl.edn")

(def hc-change-picker        ["changed_file_picker"])
(def mc-open-file            ["OpenFile"])
(def hc-file-picker          ["file_picker"])
(def mc-new-open-file        ["AddTab,OpenFile"])
(def hc-buffer-picker        ["buffer_picker"])
(def mc-cmd                  ["CommandMode"])

(defn rctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "RControl Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [c/kwp_al      [c/kw_al]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_ar      [c/kw_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_au      [c/kw_au]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_ad      [c/kw_ad]]

    ^{:doc/actions [{}]}                                                                           [c/kwsp_al     [c/kws_al]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_ar     [c/kws_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_au     [c/kws_au]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_ad     [c/kws_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/kwp_ob      [c/kw_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_cb      [c/kw_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_sc      [c/kw_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_qu      [c/kw_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_bl      [c/kw_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_cm      [c/kw_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_pe      [c/kw_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_sl      [c/kw_sl]]

    ^{:doc/actions [{}]}                                                                           [c/kwsp_ob     [c/kws_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_cb     [c/kws_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_sc     [c/kws_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_qu     [c/kws_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_bl     [c/kws_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_cm     [c/kws_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_pe     [c/kws_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_sl     [c/kws_sl]]

    ; TODO: annotate command line keys => history picker, etc
    ; action glyphs
    ^{:doc/actions [{:program c/hc,    :action "changed file picker",:exec hc-change-picker}
                    {:program c/mc,    :action "open file",          :exec mc-open-file}]}         [c/kwp_db      [c/kt_r]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "file picker",        :exec hc-file-picker}
                    {:program c/mc,    :action "open new file",      :exec mc-new-open-file}]}     [c/kwp_re      [c/kt_s]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "buffer picker",      :exec hc-buffer-picker}
                    {:program c/mc,    :action "command mode",       :exec mc-cmd}]}               [c/kwp_rs      [c/kt_t]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kwp_ro      [c/kw_ro]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_rc      [c/kw_rc]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_sp      [c/kw_sp]]

    ^{:doc/actions [{}]}                                                                           [c/kwsp_db     [c/kws_db]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_re     [c/kws_re]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_rs     [c/kws_rs]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_ro     [c/kws_ro]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_rc     [c/kws_rc]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_sp     [c/kws_sp]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kwp_1       [c/kw_1]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_2       [c/kw_2]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_3       [c/kw_3]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_4       [c/kw_4]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_5       [c/kw_5]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_6       [c/kw_6]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_7       [c/kw_7]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_8       [c/kw_8]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_9       [c/kw_9]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_0       [c/kw_0]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_hy      [c/kw_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_eq      [c/kw_eq]]

    ^{:doc/actions [{}]}                                                                           [c/kwsp_1      [c/kws_1]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_2      [c/kws_2]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_3      [c/kws_3]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_4      [c/kws_4]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_5      [c/kws_5]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_6      [c/kws_6]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_7      [c/kws_7]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_8      [c/kws_8]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_9      [c/kws_9]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_0      [c/kws_0]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_hy     [c/kws_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_eq     [c/kws_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kwp_a       [c/kw_a]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_b       [c/kw_b]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_c       [c/kw_c]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_d       [c/kw_d]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_e       [c/kw_e]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_f       [c/kw_f]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_g       [c/kw_g]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_h       [c/kw_h]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_i       [c/kw_i]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_j       [c/kw_j]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_k       [c/kw_k]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_l       [c/kw_l]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_m       [c/kw_m]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_n       [c/kw_n]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_o       [c/kw_o]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_p       [c/kw_p]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_q       [c/kw_q]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_r       [c/kw_r]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_s       [c/kw_s]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_t       [c/kw_t]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_u       [c/kw_u]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_v       [c/kw_v]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_w       [c/kw_w]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_x       [c/kw_x]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_y       [c/kw_y]]
    ^{:doc/actions [{}]}                                                                           [c/kwp_z       [c/kw_z]]
    ; ^{:doc/actions [{}]}                                                                           [c/kwp_rt      [c/kw_rt]]

    ^{:doc/actions [{}]}                                                                           [c/kwsp_a      [c/kws_a]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_b      [c/kws_b]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_c      [c/kws_c]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_d      [c/kws_d]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_e      [c/kws_e]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_f      [c/kws_f]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_g      [c/kws_g]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_h      [c/kws_h]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_i      [c/kws_i]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_j      [c/kws_j]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_k      [c/kws_k]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_l      [c/kws_l]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_m      [c/kws_m]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_n      [c/kws_n]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_o      [c/kws_o]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_p      [c/kws_p]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_q      [c/kws_q]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_r      [c/kws_r]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_s      [c/kws_s]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_t      [c/kws_t]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_u      [c/kws_u]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_v      [c/kws_v]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_w      [c/kws_w]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_x      [c/kws_x]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_y      [c/kws_y]]
    ^{:doc/actions [{}]}                                                                           [c/kwsp_z      [c/kws_z]]
    ; ^{:doc/actions [{}]}                                                                           [c/kwsp_rt     [c/kws_rt]]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
