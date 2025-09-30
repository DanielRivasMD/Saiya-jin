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
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_al   [c/kotcr_al]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_ar   [c/kotcr_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_au   [c/kotcr_au]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_ad   [c/kotcr_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_ob   [c/kotcr_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_cb   [c/kotcr_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_sc   [c/kotcr_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_qu   [c/kotcr_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_bl   [c/kotcr_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_cm   [c/kotcr_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_pe   [c/kotcr_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_sl   [c/kotcr_sl]]

   ; action glyphs
    ^{:doc/actions [{:program c/sys,   :action "Control center"}]}                                 [c/kotcrp_db   [c/kotcrp_lock]]
    ^{:doc/actions [{:program c/sys,   :action "Speak"}]}                                          [c/kotcrp_re   [c/kotcr_re]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Alacritty"}]}                               [c/kotcrp_rs   [:launch "Alacritty"]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Arc"}]}                                     [c/kotcrp_ro   [:launch "Arc"]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Mail"}]}                                    [c/kotcrp_rc   [:launch "Mail"]]
    ^{:doc/actions [{:program c/sys,   :action "Listen"}]}                                         [c/kotcrp_sp   [c/kwp_us]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_1    [c/kotcr_1]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_2    [c/kotcr_2]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_3    [c/kotcr_3]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_4    [c/kotcr_4]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_5    [c/kotcr_5]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_6    [c/kotcr_6]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_7    [c/kotcr_7]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_8    [c/kotcr_8]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_9    [c/kotcr_9]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_0    [c/kotcr_0]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_hy   [c/kotcr_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_eq   [c/kotcr_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_a    [c/kotcr_a]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_b    [c/kotcr_b]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_c    [c/kotcr_c]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_d    [c/kotcr_d]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_e    [c/kotcr_e]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_f    [c/kotcr_f]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_g    [c/kotcr_g]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_h    [c/kotcr_h]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_i    [c/kotcr_i]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_j    [c/kotcr_j]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_k    [c/kotcr_k]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_l    [c/kotcr_l]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_m    [c/kotcr_m]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_n    [c/kotcr_n]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_o    [c/kotcr_o]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_p    [c/kotcr_p]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_q    [c/kotcr_q]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_r    [c/kotcr_r]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_s    [c/kotcr_s]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_t    [c/kotcr_t]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_u    [c/kotcr_u]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_v    [c/kotcr_v]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_w    [c/kotcr_w]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_x    [c/kotcr_x]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_y    [c/kotcr_y]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_z    [c/kotcr_z]]
    ^{:doc/actions [{}]}                                                                           [c/kotcrp_rt   [c/kotcr_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (tab)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
