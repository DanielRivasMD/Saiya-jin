;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.rctl
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

(def out-file "rctl.edn")

(defn rctl []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "RControl Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [r/kwp_al      [r/kw_al]]
    ^{:doc/actions [{}]}                                                                           [r/kwp_ar      [r/kw_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kwp_au      [r/kw_au]]
    ^{:doc/actions [{}]}                                                                           [r/kwp_ad      [r/kw_ad]]

    ^{:doc/actions [{}]}                                                                           [r/kwsp_al     [r/kws_al]]
    ^{:doc/actions [{}]}                                                                           [r/kwsp_ar     [r/kws_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kwsp_au     [r/kws_au]]
    ^{:doc/actions [{}]}                                                                           [r/kwsp_ad     [r/kws_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kwp_ob      [t/kw_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kwp_cb      [t/kw_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kwp_sc      [t/kw_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kwp_qu      [t/kw_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kwp_bl      [t/kw_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kwp_cm      [t/kw_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kwp_pe      [t/kw_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kwp_sl      [t/kw_sl]]

    ^{:doc/actions [{}]}                                                                           [t/kwsp_ob     [t/kws_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kwsp_cb     [t/kws_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kwsp_sc     [t/kws_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kwsp_qu     [t/kws_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kwsp_bl     [t/kws_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kwsp_cm     [t/kws_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kwsp_pe     [t/kws_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kwsp_sl     [t/kws_sl]]

    ; action glyphs
    ; ^{:doc/actions [{:program c/hc,    :action "changed file picker",:exec hc-change-picker}
    ;                 {:program c/mc,    :action "open file",          :exec mc-open-file}]}         [a/kwp_db      [b/kt_r]       c/term]
    ; ^{:doc/actions [{:program c/hc,    :action "file picker",        :exec hc-file-picker}
    ;                 {:program c/mc,    :action "open new file",      :exec mc-new-open-file}]}     [a/kwp_re      [b/kt_s]       c/term]
    ; ^{:doc/actions [{:program c/hc,    :action "buffer picker",      :exec hc-buffer-picker}
    ;                 {:program c/mc,    :action "command mode",       :exec mc-cmd}]}               [a/kwp_rs      [b/kt_t]       c/term]
    ; ^{:doc/actions [{}]}                                                                           [a/kwp_ro      [a/kw_ro]]
    ; ^{:doc/actions [{}]}                                                                           [a/kwp_rc      [a/kw_rc]]
    ; ^{:doc/actions [{}]}                                                                           [a/kwp_sp      [a/kw_sp]]

    ^{:doc/actions [{}]}                                                                           [a/kwsp_db     [a/kws_db]]
    ^{:doc/actions [{}]}                                                                           [a/kwsp_re     [a/kws_re]]
    ^{:doc/actions [{}]}                                                                           [a/kwsp_rs     [a/kws_rs]]
    ^{:doc/actions [{}]}                                                                           [a/kwsp_ro     [a/kws_ro]]
    ^{:doc/actions [{}]}                                                                           [a/kwsp_rc     [a/kws_rc]]
    ^{:doc/actions [{}]}                                                                           [a/kwsp_sp     [a/kws_sp]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/kwp_1       [n/kw_1]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_2       [n/kw_2]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_3       [n/kw_3]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_4       [n/kw_4]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_5       [n/kw_5]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_6       [n/kw_6]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_7       [n/kw_7]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_8       [n/kw_8]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_9       [n/kw_9]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_0       [n/kw_0]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_hy      [n/kw_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kwp_eq      [n/kw_eq]]

    ^{:doc/actions [{}]}                                                                           [n/kwsp_1      [n/kws_1]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_2      [n/kws_2]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_3      [n/kws_3]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_4      [n/kws_4]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_5      [n/kws_5]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_6      [n/kws_6]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_7      [n/kws_7]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_8      [n/kws_8]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_9      [n/kws_9]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_0      [n/kws_0]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_hy     [n/kws_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kwsp_eq     [n/kws_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/kwp_a       [b/kw_a]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_b       [b/kw_b]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_c       [b/kw_c]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_d       [b/kw_d]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_e       [b/kw_e]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_f       [b/kw_f]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_g       [b/kw_g]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_h       [b/kw_h]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_i       [b/kw_i]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_j       [b/kw_j]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_k       [b/kw_k]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_l       [b/kw_l]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_m       [b/kw_m]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_n       [b/kw_n]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_o       [b/kw_o]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_p       [b/kw_p]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_q       [b/kw_q]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_r       [b/kw_r]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_s       [b/kw_s]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_t       [b/kw_t]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_u       [b/kw_u]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_v       [b/kw_v]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_w       [b/kw_w]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_x       [b/kw_x]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_y       [b/kw_y]]
    ^{:doc/actions [{}]}                                                                           [b/kwp_z       [b/kw_z]]
    ; ^{:doc/actions [{}]}                                                                           [b/kwp_rt      [b/kw_rt]]

    ^{:doc/actions [{}]}                                                                           [b/kwsp_a      [b/kws_a]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_b      [b/kws_b]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_c      [b/kws_c]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_d      [b/kws_d]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_e      [b/kws_e]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_f      [b/kws_f]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_g      [b/kws_g]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_h      [b/kws_h]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_i      [b/kws_i]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_j      [b/kws_j]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_k      [b/kws_k]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_l      [b/kws_l]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_m      [b/kws_m]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_n      [b/kws_n]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_o      [b/kws_o]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_p      [b/kws_p]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_q      [b/kws_q]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_r      [b/kws_r]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_s      [b/kws_s]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_t      [b/kws_t]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_u      [b/kws_u]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_v      [b/kws_v]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_w      [b/kws_w]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_x      [b/kws_x]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_y      [b/kws_y]]
    ^{:doc/actions [{}]}                                                                           [b/kwsp_z      [b/kws_z]]
    ; ^{:doc/actions [{}]}                                                                           [b/kwsp_rt     [b/kws_rt]]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (rctl)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
