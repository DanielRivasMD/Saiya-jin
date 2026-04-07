;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lcmd
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

(def out-file "lcmd.edn")

(def hc-line-start           ["goto_line_start"])
(def hc-line-end             ["goto_line_end", "move_char_right"])
(def hc-file-start           ["goto_file_start"])
(def hc-file-end             ["goto_last_line"])
(def hc-select-line-start    ["select_mode", "goto_line_start", "MODE"])
(def hc-select-line-end      ["select_mode", "goto_line_end", "MODE"])
(def hc-select-file-start    ["extend_to_file_start"])
(def hc-select-file-end      ["extend_to_file_end"])

(def lg-top                  ["gotoTop"])
(def lg-bottom               ["gotoBottom"])

(def mc-line-start           ["StartOfLine"])
(def mc-line-end             ["EndOfLine"])
(def mc-file-start           ["CursorStart"])
(def mc-file-end             ["CursorEnd"])
(def mc-select-line-start    ["SelectToStartOfLine"])
(def mc-select-line-end      ["SelectToEndOfLine"])
(def mc-select-file-start    ["SelectToStart"])
(def mc-select-file-end      ["SelectToEnd"])

(def ze-page-up              ["PageScrollUp;"])
(def ze-page-down            ["PageScrollDown;"])

(defn lcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto line start",    :exec hc-line-start}
                    {:program c/mc,    :action "goto line start",    :exec mc-line-start}]}        [r/kc_al       [b/kt_a]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto line end",      :exec hc-line-end}
                    {:program c/mc,    :action "goto line end",      :exec mc-line-end}]}          [r/kc_ar       [b/kt_e]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto file start",    :exec hc-file-start}
                    {:program c/lg,    :action "goto top",           :exec lg-top}
                    {:program c/mc,    :action "goto file start",    :exec mc-file-start}
                    {:program c/ze,    :action "page up",            :exec ze-page-up}]}           [r/kc_au       [r/k_hm]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto file end",      :exec hc-file-end}
                    {:program c/lg,    :action "goto bottom",        :exec lg-bottom}
                    {:program c/mc,    :action "goto file end",      :exec mc-file-end}
                    {:program c/ze,    :action "page down",          :exec ze-page-down}]}         [r/kc_ad       [r/k_ed]       c/term]

    ^{:doc/actions [{:program c/hc,    :action "select line start",  :exec hc-select-line-start}
                    {:program c/mc,    :action "select line start",  :exec mc-select-line-start}]} [r/kcs_al      [r/ko_al]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "select line end",    :exec hc-select-line-end}
                    {:program c/mc,    :action "select line end",    :exec mc-select-line-end}]}   [r/kcs_ar      [r/ko_ar]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "select file start",  :exec hc-select-file-start}
                    {:program c/mc,    :action "select file start",  :exec mc-select-file-start}]} [r/kcs_au      [r/ko_au]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "select file end",    :exec hc-select-file-end}
                    {:program c/mc,    :action "select file end",    :exec mc-select-file-end}]}   [r/kcs_ad      [r/ko_ad]      c/term]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kcp_ob      [t/kc_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kcp_cb      [t/kc_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kcp_sc      [t/kc_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kcp_qu      [t/kc_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kcp_bl      [t/kc_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kcp_cm      [t/kc_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kcp_pe      [t/kc_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kcp_sl      [t/kc_sl]]

    ^{:doc/actions [{}]}                                                                           [t/kcsp_ob     [t/kcs_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kcsp_cb     [t/kcs_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kcsp_sc     [t/kcs_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kcsp_qu     [t/kcs_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kcsp_bl     [t/kcs_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kcsp_cm     [t/kcs_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kcsp_pe     [t/kcs_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kcsp_sl     [t/kcs_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [a/kcp_db      [a/kc_db]]
    ^{:doc/actions [{:program c/hp,    :action "open background"}]}                                [a/kcp_re      [a/kc_re]]
    ^{:doc/actions [{}]}                                                                           [a/kcp_rs      [a/kc_rs]]
    ^{:doc/actions [{}]}                                                                           [a/kcp_ro      [a/kc_ro]]
    ^{:doc/actions [{}]}                                                                           [a/kcp_rc      [a/kc_rc]]
    ^{:doc/actions [{}]}                                                                           [a/kcp_sp      [a/kc_sp]]

    ^{:doc/actions [{}]}                                                                           [a/kcsp_db     [a/kcs_db]]
    ^{:doc/actions [{}]}                                                                           [a/kcsp_re     [a/kcs_re]]
    ^{:doc/actions [{}]}                                                                           [a/kcsp_rs     [a/kcs_rs]]
    ^{:doc/actions [{}]}                                                                           [a/kcsp_ro     [a/kcs_ro]]
    ^{:doc/actions [{}]}                                                                           [a/kcsp_rc     [a/kcs_rc]]
    ^{:doc/actions [{}]}                                                                           [a/kcsp_sp     [a/kcs_sp]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [n/kcp_1       [n/kc_1]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_2       [n/kc_2]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_3       [n/kc_3]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_4       [n/kc_4]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_5       [n/kc_5]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_6       [n/kc_6]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_7       [n/kc_7]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_8       [n/kc_8]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_9       [n/kc_9]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_0       [n/kc_0]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_hy      [n/kc_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kcp_eq      [n/kc_eq]]

    ^{:doc/actions [{}]}                                                                           [n/kcsp_1      [n/kcs_1]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_2      [n/kcs_2]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_3      [n/kcs_3]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_4      [n/kcs_4]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_5      [n/kcs_5]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_6      [n/kcs_6]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_7      [n/kcs_7]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_8      [n/kcs_8]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_9      [n/kcs_9]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_0      [n/kcs_0]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_hy     [n/kcs_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kcsp_eq     [n/kcs_eq]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [b/kcp_a       [b/kc_a]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_b       [b/kc_b]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_c       [b/kc_c]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_d       [b/kc_d]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_e       [b/kc_e]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_f       [b/kc_f]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_g       [b/kc_g]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_h       [b/kc_h]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_i       [b/kc_i]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_j       [b/kc_j]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_k       [b/kc_k]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_l       [b/kc_l]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_m       [b/kc_m]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_n       [b/kc_n]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_o       [b/kc_o]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_p       [b/kc_p]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_q       [b/kc_q]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_r       [b/kc_r]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_s       [b/kc_s]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_t       [b/kc_t]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_u       [b/kc_u]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_v       [b/kc_v]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_w       [b/kc_w]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_x       [b/kc_x]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_y       [b/kc_y]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_z       [b/kc_z]]
    ^{:doc/actions [{}]}                                                                           [b/kcp_rt      [b/kc_rt]]

    ^{:doc/actions [{}]}                                                                           [b/kcsp_a      [b/kcs_a]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_b      [b/kcs_b]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_c      [b/kcs_c]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_d      [b/kcs_d]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_e      [b/kcs_e]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_f      [b/kcs_f]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_g      [b/kcs_g]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_h      [b/kcs_h]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_i      [b/kcs_i]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_j      [b/kcs_j]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_k      [b/kcs_k]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_l      [b/kcs_l]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_m      [b/kcs_m]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_n      [b/kcs_n]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_o      [b/kcs_o]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_p      [b/kcs_p]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_q      [b/kcs_q]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_r      [b/kcs_r]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_s      [b/kcs_s]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_t      [b/kcs_t]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_u      [b/kcs_u]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_v      [b/kcs_v]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_w      [b/kcs_w]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_x      [b/kcs_x]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_y      [b/kcs_y]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_z      [b/kcs_z]]
    ^{:doc/actions [{}]}                                                                           [b/kcsp_rt     [b/kcs_rt]]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
