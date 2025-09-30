;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; TAB => ROTC
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.tab
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "tab.edn")

(defn tab []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  ; tab
  {:des "Tab Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_al   [c/kotcs_al]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_ar   [c/kotcs_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_au   [c/kotcs_au]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_ad   [c/kotcs_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_ob   [c/kotcs_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_cb   [c/kotcs_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_sc   [c/kotcs_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_qu   [c/kotcs_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_bl   [c/kotcs_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_cm   [c/kotcs_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_pe   [c/kotcs_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_sl   [c/kotcs_sl]]

   ; action glyphs
    ^{:doc/actions [{:program c/sys,   :action "Control center"}]}                                 [c/kotcsp_db   [c/kotcsp_lock]]
    ^{:doc/actions [{:program c/sys,   :action "Speak"}]}                                          [c/kotcsp_re   [c/kotcs_re]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Alacritty"}]}                               [c/kotcsp_rs   [:launch "Alacritty"]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Arc"}]}                                     [c/kotcsp_ro   [:launch "Arc"]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Mail"}]}                                    [c/kotcsp_rc   [:launch "Mail"]]
    ^{:doc/actions [{:program c/sys,   :action "Listen"}]}                                         [c/kotcsp_sp   [c/kwp_us]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_1    [c/kotcs_1]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_2    [c/kotcs_2]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_3    [c/kotcs_3]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_4    [c/kotcs_4]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_5    [c/kotcs_5]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_6    [c/kotcs_6]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_7    [c/kotcs_7]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_8    [c/kotcs_8]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_9    [c/kotcs_9]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_0    [c/kotcs_0]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_hy   [c/kotcs_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_eq   [c/kotcs_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_a    [c/kotcs_a]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_b    [c/kotcs_b]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_c    [c/kotcs_c]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_d    [c/kotcs_d]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_e    [c/kotcs_e]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_f    [c/kotcs_f]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_g    [c/kotcs_g]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_h    [c/kotcs_h]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_i    [c/kotcs_i]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_j    [c/kotcs_j]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_k    [c/kotcs_k]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_l    [c/kotcs_l]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_m    [c/kotcs_m]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_n    [c/kotcs_n]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_o    [c/kotcs_o]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_p    [c/kotcs_p]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_q    [c/kotcs_q]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_r    [c/kotcs_r]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_s    [c/kotcs_s]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_t    [c/kotcs_t]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_u    [c/kotcs_u]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_v    [c/kotcs_v]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_w    [c/kotcs_w]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_x    [c/kotcs_x]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_y    [c/kotcs_y]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_z    [c/kotcs_z]]
    ^{:doc/actions [{}]}                                                                           [c/kotcsp_rt   [c/kotcs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (tab)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
