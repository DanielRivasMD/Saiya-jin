;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; JOKER => EWQ
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.joker
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "joker.edn")

(defn joker []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Joker Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [c/kewqp_al     [c/kewq_al]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_ar     [c/kewq_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_au     [c/kewq_au]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_ad     [c/kewq_ad]]

    ^{:doc/actions [{}]}                                                                           [c/kewqsp_al   [c/kewqs_al]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_ar   [c/kewqs_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_au   [c/kewqs_au]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_ad   [c/kewqs_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/kewqp_ob    [c/ks_cm c/ks_cm]]                                                        ; '<<'
    ^{:doc/actions [{}]}                                                                           [c/kewqp_cb    [c/ks_pe c/ks_pe]]                                                     ; '>>'
    ^{:doc/actions [{}]}                                                                           [c/kewqp_sc    [c/kewq_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_qu    [c/ks_sc c/ks_sc]]                                                       ; '::'
    ^{:doc/actions [{}]}                                                                           [c/kewqp_bl    [c/ks_bl c/ks_bl]]                                                   ; '||'
    ^{:doc/actions [{}]}                                                                           [c/kewqp_cm    [c/k_pe c/k_pe]]                                                                 ; '..'
    ^{:doc/actions [{}]}                                                                           [c/kewqp_pe    [c/k_pe c/k_pe c/k_pe]]                                                        ; '...'
    ^{:doc/actions [{}]}                                                                           [c/kewqp_sl    [c/ks_1 c/ks_1]]                                                                       ; '!!'

    ^{:doc/actions [{}]}                                                                           [c/kewqsp_ob   [c/kewqs_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_cb   [c/kewqs_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_sc   [c/kewqs_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_qu   [c/kewqs_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_bl   [c/kewqs_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_cm   [c/kewqs_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_pe   [c/kewqs_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_sl   [c/kewqs_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [c/kewqp_db    [c/kewq_db]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_re    [c/k_eq c/k_eq]]                                               ; '=='
    ^{:doc/actions [{}]}                                                                           [c/kewqp_rs    [c/kewq_rs]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_ro    [c/ks_8 c/ks_8]]                                                                ; '**'
    ^{:doc/actions [{}]}                                                                           [c/kewqp_rc    [c/k_sl c/k_sl]]                                                           ; '//'
    ^{:doc/actions [{}]}                                                                           [c/kewqp_sp    [c/kewq_sp]]

    ^{:doc/actions [{}]}                                                                           [c/kewqsp_db   [c/kewqs_db]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_re   [c/kewqs_re]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_rs   [c/kewqs_rs]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_ro   [c/kewqs_ro]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_rc   [c/kewqs_rc]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_sp   [c/kewqs_sp]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kewqp_1     [c/kewq_1]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_2     [c/kewq_2]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_3     [c/kewq_3]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_4     [c/kewq_4]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_5     [c/kewq_5]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_6     [c/kewq_6]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_7     [c/kewq_7]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_8     [c/kewq_8]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_9     [c/kewq_9]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_0     [c/kewq_0]]
    ^{:doc/actions [{}]}                                                                           [:!EWQ#Phyphen [:hyphen :hyphen]]                                                                ; '--'
    ^{:doc/actions [{}]}                                                                           [:!EWQ#Pequal_sign [:!Sequal_sign :!Sequal_sign]]                                                ; '++'

    ^{:doc/actions [{}]}                                                                           [c/kewqsp_1    [c/kewqs_1]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_2    [c/kewqs_2]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_3    [c/kewqs_3]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_4    [c/kewqs_4]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_5    [c/kewqs_5]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_6    [c/kewqs_6]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_7    [c/kewqs_7]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_8    [c/kewqs_8]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_9    [c/kewqs_9]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_0    [c/kewqs_0]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_hy   [c/kewqs_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_eq   [c/kewqs_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [c/kewqp_a     [c/kewq_a]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_b     [c/kewq_b]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_c     [c/kewq_c]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_d     [c/kewq_d]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_e     [c/kewq_e]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_f     [c/kewq_f]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_g     [c/kewq_g]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_h     [c/kewq_h]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_i     [c/kewq_i]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_j     [c/kewq_j]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_k     [c/kewq_k]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_l     [c/kewq_l]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_m     [c/kewq_m]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_n     [c/kewq_n]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_o     [c/kewq_o]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_p     [c/kewq_p]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_q     [c/kewq_q]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_r     [c/kewq_r]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_s     [c/kewq_s]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_t     [c/kewq_t]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_u     [c/kewq_u]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_v     [c/kewq_v]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_w     [c/kewq_w]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_x     [c/kewq_x]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_y     [c/kewq_y]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_z     [c/kewq_z]]
    ^{:doc/actions [{}]}                                                                           [c/kewqp_rt    [c/kewq_rt]]

    ^{:doc/actions [{}]}                                                                           [c/kewqsp_a    [c/kewqs_a]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_b    [c/kewqs_b]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_c    [c/kewqs_c]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_d    [c/kewqs_d]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_e    [c/kewqs_e]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_f    [c/kewqs_f]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_g    [c/kewqs_g]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_h    [c/kewqs_h]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_i    [c/kewqs_i]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_j    [c/kewqs_j]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_k    [c/kewqs_k]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_l    [c/kewqs_l]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_m    [c/kewqs_m]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_n    [c/kewqs_n]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_o    [c/kewqs_o]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_p    [c/kewqs_p]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_q    [c/kewqs_q]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_r    [c/kewqs_r]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_s    [c/kewqs_s]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_t    [c/kewqs_t]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_u    [c/kewqs_u]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_v    [c/kewqs_v]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_w    [c/kewqs_w]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_x    [c/kewqs_x]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_y    [c/kewqs_y]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_z    [c/kewqs_z]]
    ^{:doc/actions [{}]}                                                                           [c/kewqsp_rt   [c/kewqs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (joker)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
