;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL-CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lctlcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
						))

(def out-file "lctlcmd.edn")

(def hc-prev-tab             ["goto_previous_buffer"])
(def lg-prev-tab             ["prevTab"])
(def mc-prev-tab             ["PreviousTab"])
(def ze-move-left            ["MovePane \"Left\";"])
(def hc-next-tab             ["goto_next_buffer"])
(def lg-next-tab             ["nextTab"])
(def mc-next-tab             ["NextTab"])
(def ze-move-rigth           ["MovePane \"Right\";"])
(def hc-inc                  ["increment"])
(def lg-prev-block           ["prevBlock-alt2"])
(def ze-move-up              ["MovePane \"Up\";"])
(def hc-dec                  ["decrement"])
(def lg-next-block           ["nextBlock-alt2"])
(def ze-move-down            ["MovePane \"Down\";"])
(def hc-close-tab            [":buffer-close"])
(def mc-close-tab            ["Quit"])
(def hc-unsplit              ["wonly"])
(def mc-unsplit              ["Unsplit"])
(def hc-vsplit               ["vsplit"])
(def mc-vsplit               ["VSplit"])
(def hc-hsplit               ["hsplit"])
(def mc-hsplit               ["HSplit"])
(def hc-close-split          ["wclose"])
(def mc-close-split          ["Unsplit"])
(def hc-last-tab             ["goto_last_accessed_file"])
(def br-toggle-preview       [])

(defn lctlcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control - Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "jump prev buffer",   :exec hc-prev-tab}
                    {:program c/lg,    :action "jump prev tab",      :exec lg-prev-tab}
                    {:program c/mc,    :action "jump prev buffer",   :exec mc-prev-tab}
                    {:program c/ze,    :action "move left",          :exec ze-move-left}]}         [r/ktcp_al     [b/kt_b]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump next buffer",   :exec hc-next-tab}
                    {:program c/lg,    :action "jump next tab",      :exec lg-next-tab}
                    {:program c/mc,    :action "jump next buffer",   :exec mc-next-tab}
                    {:program c/ze,    :action "move right",         :exec ze-move-rigth}]}        [r/ktcp_ar     [b/kt_f]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "increment number",   :exec hc-inc}
                    {:program c/lg,    :action "jump prev block",    :exec lg-prev-block}
                    {:program c/ze,    :action "move up",            :exec ze-move-up}]}           [r/ktcp_au     [b/kt_n]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "decrement number",   :exec hc-dec}
                    {:program c/lg,    :action "jump next block",    :exec lg-next-block}
                    {:program c/ze,    :action "move down",          :exec ze-move-down}]}         [r/ktcp_ad     [b/kt_p]       c/term]

    ^{:doc/actions [{}]}                                                                           [r/ktcsp_al    [r/ktcs_al]]
    ^{:doc/actions [{}]}                                                                           [r/ktcsp_ar    [r/ktcs_ar]]
    ^{:doc/actions [{}]}                                                                           [r/ktcsp_au    [r/ktcs_au]]
    ^{:doc/actions [{}]}                                                                           [r/ktcsp_ad    [r/ktcs_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/ktcp_ob     [t/ktc_ob]]
    ^{:doc/actions [{}]}                                                                           [t/ktcp_cb     [t/ktc_cb]]
    ^{:doc/actions [{}]}                                                                           [t/ktcp_sc     [t/ktc_sc]]
    ^{:doc/actions [{}]}                                                                           [t/ktcp_qu     [t/ktc_qu]]
    ^{:doc/actions [{}]}                                                                           [t/ktcp_bl     [t/ktc_bl]]
    ^{:doc/actions [{}]}                                                                           [t/ktcp_cm     [t/ktc_cm]]
    ^{:doc/actions [{}]}                                                                           [t/ktcp_pe     [t/ktc_pe]]
    ^{:doc/actions [{}]}                                                                           [t/ktcp_sl     [t/ktc_sl]]

    ^{:doc/actions [{}]}                                                                           [t/ktcsp_ob    [t/ktcs_ob]]
    ^{:doc/actions [{}]}                                                                           [t/ktcsp_cb    [t/ktcs_cb]]
    ^{:doc/actions [{}]}                                                                           [t/ktcsp_sc    [t/ktcs_sc]]
    ^{:doc/actions [{}]}                                                                           [t/ktcsp_qu    [t/ktcs_qu]]
    ^{:doc/actions [{}]}                                                                           [t/ktcsp_bl    [t/ktcs_bl]]
    ^{:doc/actions [{}]}                                                                           [t/ktcsp_cm    [t/ktcs_cm]]
    ^{:doc/actions [{}]}                                                                           [t/ktcsp_pe    [t/ktcs_pe]]
    ^{:doc/actions [{}]}                                                                           [t/ktcsp_sl    [t/ktcs_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/hc,    :action "close tab",          :exec hc-close-tab}
                    {:program c/mc,    :action "close tab",          :exec mc-close-tab}]}         [a/ktcp_db     [b/kt_l]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "close others",       :exec hc-unsplit}
                    {:program c/mc,    :action "close others",       :exec mc-unsplit}]}           [a/ktcp_re     [b/kt_g]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "split right",        :exec hc-vsplit}
                    {:program c/mc,    :action "split right",        :exec mc-vsplit}]}            [a/ktcp_rs     [b/kt_v]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "split down",         :exec hc-hsplit}
                    {:program c/mc,    :action "split down",         :exec mc-hsplit}]}            [a/ktcp_ro     [b/kt_h]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "close window",       :exec hc-close-split}
                    {:program c/mc,    :action "close window",       :exec mc-close-split}]}       [a/ktcp_rc     [b/kt_j]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "last file",          :exec hc-last-tab}
                    {:program c/br,    :action "open preview"        :exec br-toggle-preview}]}    [a/ktcp_sp     [b/kt_o]       c/term]

    ^{:doc/actions [{}]}                                                                           [a/ktcsp_db    [a/ktcs_db]]
    ^{:doc/actions [{}]}                                                                           [a/ktcsp_re    [a/ktcs_re]]
    ^{:doc/actions [{}]}                                                                           [a/ktcsp_rs    [a/ktcs_rs]]
    ^{:doc/actions [{}]}                                                                           [a/ktcsp_ro    [a/ktcs_ro]]
    ^{:doc/actions [{}]}                                                                           [a/ktcsp_rc    [a/ktcs_rc]]
    ^{:doc/actions [{}]}                                                                           [a/ktcsp_sp    [a/ktcs_sp]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/ktcp_1      [n/ktc_1]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_2      [n/ktc_2]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_3      [n/ktc_3]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_4      [n/ktc_4]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_5      [n/ktc_5]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_6      [n/ktc_6]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_7      [n/ktc_7]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_8      [n/ktc_8]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_9      [n/ktc_9]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_0      [n/ktc_0]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_hy     [n/ktc_hy]]
    ^{:doc/actions [{}]}                                                                           [n/ktcp_eq     [n/ktc_eq]]

    ^{:doc/actions [{}]}                                                                           [n/ktcsp_1     [n/ktcs_1]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_2     [n/ktcs_2]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_3     [n/ktcs_3]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_4     [n/ktcs_4]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_5     [n/ktcs_5]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_6     [n/ktcs_6]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_7     [n/ktcs_7]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_8     [n/ktcs_8]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_9     [n/ktcs_9]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_0     [n/ktcs_0]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_hy    [n/ktcs_hy]]
    ^{:doc/actions [{}]}                                                                           [n/ktcsp_eq    [n/ktcs_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/ktcp_a      [b/ktc_a]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_b      [b/ktc_b]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_c      [b/ktc_c]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_d      [b/ktc_d]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_e      [b/ktc_e]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_f      [b/ktc_f]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_g      [b/ktc_g]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_h      [b/ktc_h]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_i      [b/ktc_i]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_j      [b/ktc_j]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_k      [b/ktc_k]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_l      [b/ktc_l]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_m      [b/ktc_m]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_n      [b/ktc_n]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_o      [b/ktc_o]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_p      [b/ktc_p]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_q      [b/ktc_q]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_r      [b/ktc_r]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_s      [b/ktc_s]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_t      [b/ktc_t]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_u      [b/ktc_u]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_v      [b/ktc_v]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_w      [b/ktc_w]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_x      [b/ktc_x]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_y      [b/ktc_y]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_z      [b/ktc_z]]
    ^{:doc/actions [{}]}                                                                           [b/ktcp_rt     [b/ktc_rt]]

    ^{:doc/actions [{}]}                                                                           [b/ktcsp_a     [b/ktcs_a]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_b     [b/ktcs_b]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_c     [b/ktcs_c]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_d     [b/ktcs_d]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_e     [b/ktcs_e]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_f     [b/ktcs_f]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_g     [b/ktcs_g]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_h     [b/ktcs_h]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_i     [b/ktcs_i]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_j     [b/ktcs_j]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_k     [b/ktcs_k]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_l     [b/ktcs_l]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_m     [b/ktcs_m]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_n     [b/ktcs_n]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_o     [b/ktcs_o]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_p     [b/ktcs_p]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_q     [b/ktcs_q]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_r     [b/ktcs_r]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_s     [b/ktcs_s]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_t     [b/ktcs_t]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_u     [b/ktcs_u]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_v     [b/ktcs_v]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_w     [b/ktcs_w]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_x     [b/ktcs_x]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_y     [b/ktcs_y]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_z     [b/ktcs_z]]
    ^{:doc/actions [{}]}                                                                           [b/ktcsp_rt    [b/ktcs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lctlcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
