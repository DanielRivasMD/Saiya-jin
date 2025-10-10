;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; JOKER => EWQ
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
						))

(def out-file "joker.edn")

(defn joker []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Joker Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [r/kewqp_al     [r/kewq_al]]
    ^{:doc/actions [{}]}                                                                           [r/kewqp_ar     [r/kewq_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kewqp_au     [r/kewq_au]]
    ^{:doc/actions [{}]}                                                                           [r/kewqp_ad     [r/kewq_ad]]

    ^{:doc/actions [{}]}                                                                           [r/kewqsp_al   [r/kewqs_al]]
    ^{:doc/actions [{}]}                                                                           [r/kewqsp_ar   [r/kewqs_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kewqsp_au   [r/kewqs_au]]
    ^{:doc/actions [{}]}                                                                           [r/kewqsp_ad   [r/kewqs_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kewqp_ob    [t/ks_cm t/ks_cm]]                                                        ; '<<'
    ^{:doc/actions [{}]}                                                                           [t/kewqp_cb    [t/ks_pe t/ks_pe]]                                                     ; '>>'
    ^{:doc/actions [{}]}                                                                           [t/kewqp_sc    [t/kewq_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kewqp_qu    [t/ks_sc t/ks_sc]]                                                       ; '::'
    ^{:doc/actions [{}]}                                                                           [t/kewqp_bl    [t/ks_bl t/ks_bl]]                                                   ; '||'
    ^{:doc/actions [{}]}                                                                           [t/kewqp_cm    [t/k_pe t/k_pe]]                                                                 ; '..'
    ^{:doc/actions [{}]}                                                                           [t/kewqp_pe    [t/k_pe t/k_pe t/k_pe]]                                                        ; '...'
    ^{:doc/actions [{}]}                                                                           [t/kewqp_sl    [n/ks_1 n/ks_1]]                                                                       ; '!!'

    ^{:doc/actions [{}]}                                                                           [t/kewqsp_ob   [t/kewqs_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kewqsp_cb   [t/kewqs_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kewqsp_sc   [t/kewqs_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kewqsp_qu   [t/kewqs_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kewqsp_bl   [t/kewqs_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kewqsp_cm   [t/kewqs_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kewqsp_pe   [t/kewqs_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kewqsp_sl   [t/kewqs_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [a/kewqp_db    [a/kewq_db]]
    ^{:doc/actions [{}]}                                                                           [a/kewqp_re    [n/k_eq n/k_eq]]                                               ; '=='
    ^{:doc/actions [{}]}                                                                           [a/kewqp_rs    [a/kewq_rs]]
    ^{:doc/actions [{}]}                                                                           [a/kewqp_ro    [n/ks_8 n/ks_8]]                                                                ; '**'
    ^{:doc/actions [{}]}                                                                           [a/kewqp_rc    [t/k_sl t/k_sl]]                                                           ; '//'
    ^{:doc/actions [{}]}                                                                           [a/kewqp_sp    [a/kewq_sp]]

    ^{:doc/actions [{}]}                                                                           [a/kewqsp_db   [a/kewqs_db]]
    ^{:doc/actions [{}]}                                                                           [a/kewqsp_re   [a/kewqs_re]]
    ^{:doc/actions [{}]}                                                                           [a/kewqsp_rs   [a/kewqs_rs]]
    ^{:doc/actions [{}]}                                                                           [a/kewqsp_ro   [a/kewqs_ro]]
    ^{:doc/actions [{}]}                                                                           [a/kewqsp_rc   [a/kewqs_rc]]
    ^{:doc/actions [{}]}                                                                           [a/kewqsp_sp   [a/kewqs_sp]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/kewqp_1     [n/kewq_1]]
    ^{:doc/actions [{}]}                                                                           [n/kewqp_2     [n/kewq_2]]
    ^{:doc/actions [{}]}                                                                           [n/kewqp_3     [n/kewq_3]]
    ^{:doc/actions [{}]}                                                                           [n/kewqp_4     [n/kewq_4]]
    ^{:doc/actions [{}]}                                                                           [n/kewqp_5     [n/kewq_5]]
    ^{:doc/actions [{}]}                                                                           [n/kewqp_6     [n/kewq_6]]
    ^{:doc/actions [{}]}                                                                           [n/kewqp_7     [n/kewq_7]]
    ^{:doc/actions [{}]}                                                                           [n/kewqp_8     [n/kewq_8]]
    ^{:doc/actions [{}]}                                                                           [n/kewqp_9     [n/kewq_9]]
    ^{:doc/actions [{}]}                                                                           [n/kewqp_0     [n/kewq_0]]
    ^{:doc/actions [{}]}                                                                           [:!EWQ#Phyphen [:hyphen :hyphen]]                                                                ; '--'
    ^{:doc/actions [{}]}                                                                           [:!EWQ#Pequal_sign [:!Sequal_sign :!Sequal_sign]]                                                ; '++'

    ^{:doc/actions [{}]}                                                                           [n/kewqsp_1    [n/kewqs_1]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_2    [n/kewqs_2]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_3    [n/kewqs_3]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_4    [n/kewqs_4]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_5    [n/kewqs_5]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_6    [n/kewqs_6]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_7    [n/kewqs_7]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_8    [n/kewqs_8]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_9    [n/kewqs_9]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_0    [n/kewqs_0]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_hy   [n/kewqs_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kewqsp_eq   [n/kewqs_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/kewqp_a     [b/kewq_a]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_b     [b/kewq_b]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_c     [b/kewq_c]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_d     [b/kewq_d]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_e     [b/kewq_e]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_f     [b/kewq_f]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_g     [b/kewq_g]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_h     [b/kewq_h]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_i     [b/kewq_i]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_j     [b/kewq_j]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_k     [b/kewq_k]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_l     [b/kewq_l]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_m     [b/kewq_m]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_n     [b/kewq_n]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_o     [b/kewq_o]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_p     [b/kewq_p]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_q     [b/kewq_q]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_r     [b/kewq_r]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_s     [b/kewq_s]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_t     [b/kewq_t]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_u     [b/kewq_u]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_v     [b/kewq_v]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_w     [b/kewq_w]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_x     [b/kewq_x]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_y     [b/kewq_y]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_z     [b/kewq_z]]
    ^{:doc/actions [{}]}                                                                           [b/kewqp_rt    [b/kewq_rt]]

    ^{:doc/actions [{}]}                                                                           [b/kewqsp_a    [b/kewqs_a]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_b    [b/kewqs_b]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_c    [b/kewqs_c]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_d    [b/kewqs_d]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_e    [b/kewqs_e]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_f    [b/kewqs_f]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_g    [b/kewqs_g]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_h    [b/kewqs_h]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_i    [b/kewqs_i]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_j    [b/kewqs_j]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_k    [b/kewqs_k]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_l    [b/kewqs_l]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_m    [b/kewqs_m]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_n    [b/kewqs_n]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_o    [b/kewqs_o]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_p    [b/kewqs_p]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_q    [b/kewqs_q]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_r    [b/kewqs_r]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_s    [b/kewqs_s]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_t    [b/kewqs_t]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_u    [b/kewqs_u]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_v    [b/kewqs_v]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_w    [b/kewqs_w]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_x    [b/kewqs_x]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_y    [b/kewqs_y]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_z    [b/kewqs_z]]
    ^{:doc/actions [{}]}                                                                           [b/kewqsp_rt   [b/kewqs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (joker)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
