;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; arrows
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns config.arrows
(:require
            [config.config :as c]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; arrow glyphs
(def k_au (c/mk c/_au))
(def k_ad (c/mk c/_ad))
(def k_al (c/mk c/_al))
(def k_ar (c/mk c/_ar))

(def ko_au (c/mk c/K c/O c/_au))
(def ko_ad (c/mk c/K c/O c/_ad))
(def ko_al (c/mk c/K c/O c/_al))
(def ko_ar (c/mk c/K c/O c/_ar))

(def kt_au (c/mk c/K c/T c/_au))
(def kt_ad (c/mk c/K c/T c/_ad))
(def kt_al (c/mk c/K c/T c/_al))
(def kt_ar (c/mk c/K c/T c/_ar))

(def kc_au (c/mk c/K c/C c/_au))
(def kc_ad (c/mk c/K c/C c/_ad))
(def kc_al (c/mk c/K c/C c/_al))
(def kc_ar (c/mk c/K c/C c/_ar))

(def kot_au (c/mk c/K c/O c/T c/_au))
(def kot_ad (c/mk c/K c/O c/T c/_ad))
(def kot_al (c/mk c/K c/O c/T c/_al))
(def kot_ar (c/mk c/K c/O c/T c/_ar))

(def koc_au (c/mk c/K c/O c/C c/_au))
(def koc_ad (c/mk c/K c/O c/C c/_ad))
(def koc_al (c/mk c/K c/O c/C c/_al))
(def koc_ar (c/mk c/K c/O c/C c/_ar))

(def ktc_au (c/mk c/K c/T c/C c/_au))
(def ktc_ad (c/mk c/K c/T c/C c/_ad))
(def ktc_al (c/mk c/K c/T c/C c/_al))
(def ktc_ar (c/mk c/K c/T c/C c/_ar))

(def kotc_au (c/mk c/K c/O c/T c/C c/_au))
(def kotc_ad (c/mk c/K c/O c/T c/C c/_ad))
(def kotc_al (c/mk c/K c/O c/T c/C c/_al))
(def kotc_ar (c/mk c/K c/O c/T c/C c/_ar))

(def ke_au (c/mk c/K c/E c/_au))
(def ke_ad (c/mk c/K c/E c/_ad))
(def ke_al (c/mk c/K c/E c/_al))
(def ke_ar (c/mk c/K c/E c/_ar))

(def kw_au (c/mk c/K c/W c/_au))
(def kw_ad (c/mk c/K c/W c/_ad))
(def kw_al (c/mk c/K c/W c/_al))
(def kw_ar (c/mk c/K c/W c/_ar))

(def kq_au (c/mk c/K c/Q c/_au))
(def kq_ad (c/mk c/K c/Q c/_ad))
(def kq_al (c/mk c/K c/Q c/_al))
(def kq_ar (c/mk c/K c/Q c/_ar))

(def kewq_au (c/mk c/K c/E c/W c/Q c/_au))
(def kewq_ad (c/mk c/K c/E c/W c/Q c/_ad))
(def kewq_al (c/mk c/K c/E c/W c/Q c/_al))
(def kewq_ar (c/mk c/K c/E c/W c/Q c/_ar))

(def ks_au (c/mk c/K c/S c/_au))
(def ks_ad (c/mk c/K c/S c/_ad))
(def ks_al (c/mk c/K c/S c/_al))
(def ks_ar (c/mk c/K c/S c/_ar))

(def kos_au (c/mk c/K c/O c/S c/_au))
(def kos_ad (c/mk c/K c/O c/S c/_ad))
(def kos_al (c/mk c/K c/O c/S c/_al))
(def kos_ar (c/mk c/K c/O c/S c/_ar))

(def kts_au (c/mk c/K c/T c/S c/_au))
(def kts_ad (c/mk c/K c/T c/S c/_ad))
(def kts_al (c/mk c/K c/T c/S c/_al))
(def kts_ar (c/mk c/K c/T c/S c/_ar))

(def kcs_au (c/mk c/K c/C c/S c/_au))
(def kcs_ad (c/mk c/K c/C c/S c/_ad))
(def kcs_al (c/mk c/K c/C c/S c/_al))
(def kcs_ar (c/mk c/K c/C c/S c/_ar))

(def kots_au (c/mk c/K c/O c/T c/S c/_au))
(def kots_ad (c/mk c/K c/O c/T c/S c/_ad))
(def kots_al (c/mk c/K c/O c/T c/S c/_al))
(def kots_ar (c/mk c/K c/O c/T c/S c/_ar))

(def kocs_au (c/mk c/K c/O c/C c/S c/_au))
(def kocs_ad (c/mk c/K c/O c/C c/S c/_ad))
(def kocs_al (c/mk c/K c/O c/C c/S c/_al))
(def kocs_ar (c/mk c/K c/O c/C c/S c/_ar))

(def ktcs_au (c/mk c/K c/T c/C c/S c/_au))
(def ktcs_ad (c/mk c/K c/T c/C c/S c/_ad))
(def ktcs_al (c/mk c/K c/T c/C c/S c/_al))
(def ktcs_ar (c/mk c/K c/T c/C c/S c/_ar))

(def kotcs_au (c/mk c/K c/O c/T c/C c/R c/_au))
(def kotcs_ad (c/mk c/K c/O c/T c/C c/R c/_ad))
(def kotcs_al (c/mk c/K c/O c/T c/C c/R c/_al))
(def kotcs_ar (c/mk c/K c/O c/T c/C c/R c/_ar))

(def ker_au (c/mk c/K c/E c/R c/_au))
(def ker_ad (c/mk c/K c/E c/R c/_ad))
(def ker_al (c/mk c/K c/E c/R c/_al))
(def ker_ar (c/mk c/K c/E c/R c/_ar))

(def kws_au (c/mk c/K c/W c/S c/_au))
(def kws_ad (c/mk c/K c/W c/S c/_ad))
(def kws_al (c/mk c/K c/W c/S c/_al))
(def kws_ar (c/mk c/K c/W c/S c/_ar))

(def kqr_au (c/mk c/K c/Q c/R c/_au))
(def kqr_ad (c/mk c/K c/Q c/R c/_ad))
(def kqr_al (c/mk c/K c/Q c/R c/_al))
(def kqr_ar (c/mk c/K c/Q c/R c/_ar))

(def kewqs_au (c/mk c/K c/E c/W c/Q c/S c/_au))
(def kewqs_ad (c/mk c/K c/E c/W c/Q c/S c/_ad))
(def kewqs_al (c/mk c/K c/E c/W c/Q c/S c/_al))
(def kewqs_ar (c/mk c/K c/E c/W c/Q c/S c/_ar))

(def kp_au   (c/mk c/P c/_au))
(def kp_ad   (c/mk c/P c/_ad))
(def kp_al   (c/mk c/P c/_al))
(def kp_ar   (c/mk c/P c/_ar))

(def kop_au  (c/mk c/K c/O c/P c/_au))
(def kop_ad  (c/mk c/K c/O c/P c/_ad))
(def kop_al  (c/mk c/K c/O c/P c/_al))
(def kop_ar  (c/mk c/K c/O c/P c/_ar))

(def ktp_au  (c/mk c/K c/T c/P c/_au))
(def ktp_ad  (c/mk c/K c/T c/P c/_ad))
(def ktp_al  (c/mk c/K c/T c/P c/_al))
(def ktp_ar  (c/mk c/K c/T c/P c/_ar))

(def kcp_au  (c/mk c/K c/C c/P c/_au))
(def kcp_ad  (c/mk c/K c/C c/P c/_ad))
(def kcp_al  (c/mk c/K c/C c/P c/_al))
(def kcp_ar  (c/mk c/K c/C c/P c/_ar))

(def kotp_au (c/mk c/K c/O c/T c/P c/_au))
(def kotp_ad (c/mk c/K c/O c/T c/P c/_ad))
(def kotp_al (c/mk c/K c/O c/T c/P c/_al))
(def kotp_ar (c/mk c/K c/O c/T c/P c/_ar))

(def kocp_au (c/mk c/K c/O c/C c/P c/_au))
(def kocp_ad (c/mk c/K c/O c/C c/P c/_ad))
(def kocp_al (c/mk c/K c/O c/C c/P c/_al))
(def kocp_ar (c/mk c/K c/O c/C c/P c/_ar))

(def ktcp_au (c/mk c/K c/T c/C c/P c/_au))
(def ktcp_ad (c/mk c/K c/T c/C c/P c/_ad))
(def ktcp_al (c/mk c/K c/T c/C c/P c/_al))
(def ktcp_ar (c/mk c/K c/T c/C c/P c/_ar))

(def kotcp_au (c/mk c/K c/O c/T c/C c/P c/_au))
(def kotcp_ad (c/mk c/K c/O c/T c/C c/P c/_ad))
(def kotcp_al (c/mk c/K c/O c/T c/C c/P c/_al))
(def kotcp_ar (c/mk c/K c/O c/T c/C c/P c/_ar))

(def kep_au  (c/mk c/K c/E c/P c/_au))
(def kep_ad  (c/mk c/K c/E c/P c/_ad))
(def kep_al  (c/mk c/K c/E c/P c/_al))
(def kep_ar  (c/mk c/K c/E c/P c/_ar))

(def kwp_au  (c/mk c/K c/W c/P c/_au))
(def kwp_ad  (c/mk c/K c/W c/P c/_ad))
(def kwp_al  (c/mk c/K c/W c/P c/_al))
(def kwp_ar  (c/mk c/K c/W c/P c/_ar))

(def kqp_au  (c/mk c/K c/Q c/P c/_au))
(def kqp_ad  (c/mk c/K c/Q c/P c/_ad))
(def kqp_al  (c/mk c/K c/Q c/P c/_al))
(def kqp_ar  (c/mk c/K c/Q c/P c/_ar))

(def kewqp_au  (c/mk c/K c/E c/W c/Q c/P c/_au))
(def kewqp_ad  (c/mk c/K c/E c/W c/Q c/P c/_ad))
(def kewqp_al  (c/mk c/K c/E c/W c/Q c/P c/_al))
(def kewqp_ar  (c/mk c/K c/E c/W c/Q c/P c/_ar))

(def ksp_au  (c/mk c/K c/S c/P c/_au))
(def ksp_ad  (c/mk c/K c/S c/P c/_ad))
(def ksp_al  (c/mk c/K c/S c/P c/_al))
(def ksp_ar  (c/mk c/K c/S c/P c/_ar))

(def kosp_au (c/mk c/K c/O c/S c/P c/_au))
(def kosp_ad (c/mk c/K c/O c/S c/P c/_ad))
(def kosp_al (c/mk c/K c/O c/S c/P c/_al))
(def kosp_ar (c/mk c/K c/O c/S c/P c/_ar))

(def ktsp_au (c/mk c/K c/T c/S c/P c/_au))
(def ktsp_ad (c/mk c/K c/T c/S c/P c/_ad))
(def ktsp_al (c/mk c/K c/T c/S c/P c/_al))
(def ktsp_ar (c/mk c/K c/T c/S c/P c/_ar))

(def kcsp_au (c/mk c/K c/C c/S c/P c/_au))
(def kcsp_ad (c/mk c/K c/C c/S c/P c/_ad))
(def kcsp_al (c/mk c/K c/C c/S c/P c/_al))
(def kcsp_ar (c/mk c/K c/C c/S c/P c/_ar))

(def kotsp_au (c/mk c/K c/O c/T c/S c/P c/_au))
(def kotsp_ad (c/mk c/K c/O c/T c/S c/P c/_ad))
(def kotsp_al (c/mk c/K c/O c/T c/S c/P c/_al))
(def kotsp_ar (c/mk c/K c/O c/T c/S c/P c/_ar))

(def kocsp_au (c/mk c/K c/O c/C c/S c/P c/_au))
(def kocsp_ad (c/mk c/K c/O c/C c/S c/P c/_ad))
(def kocsp_al (c/mk c/K c/O c/C c/S c/P c/_al))
(def kocsp_ar (c/mk c/K c/O c/C c/S c/P c/_ar))

(def ktcsp_au (c/mk c/K c/T c/C c/S c/P c/_au))
(def ktcsp_ad (c/mk c/K c/T c/C c/S c/P c/_ad))
(def ktcsp_al (c/mk c/K c/T c/C c/S c/P c/_al))
(def ktcsp_ar (c/mk c/K c/T c/C c/S c/P c/_ar))

(def kotcsp_au (c/mk c/K c/O c/T c/C c/R c/P c/_au))
(def kotcsp_ad (c/mk c/K c/O c/T c/C c/R c/P c/_ad))
(def kotcsp_al (c/mk c/K c/O c/T c/C c/R c/P c/_al))
(def kotcsp_ar (c/mk c/K c/O c/T c/C c/R c/P c/_ar))

(def kerp_au (c/mk c/K c/E c/R c/P c/_au))
(def kerp_ad (c/mk c/K c/E c/R c/P c/_ad))
(def kerp_al (c/mk c/K c/E c/R c/P c/_al))
(def kerp_ar (c/mk c/K c/E c/R c/P c/_ar))

(def kwsp_au (c/mk c/K c/W c/S c/P c/_au))
(def kwsp_ad (c/mk c/K c/W c/S c/P c/_ad))
(def kwsp_al (c/mk c/K c/W c/S c/P c/_al))
(def kwsp_ar (c/mk c/K c/W c/S c/P c/_ar))

(def kqrp_au (c/mk c/K c/Q c/R c/P c/_au))
(def kqrp_ad (c/mk c/K c/Q c/R c/P c/_ad))
(def kqrp_al (c/mk c/K c/Q c/R c/P c/_al))
(def kqrp_ar (c/mk c/K c/Q c/R c/P c/_ar))

(def kewqsp_au (c/mk c/K c/E c/W c/Q c/S c/P c/_au))
(def kewqsp_ad (c/mk c/K c/E c/W c/Q c/S c/P c/_ad))
(def kewqsp_al (c/mk c/K c/E c/W c/Q c/S c/P c/_al))
(def kewqsp_ar (c/mk c/K c/E c/W c/Q c/S c/P c/_ar))

(def k_pu (c/mk c/_pu))
(def k_pd (c/mk c/_pd))
(def k_hm (c/mk c/_hm))
(def k_ed (c/mk c/_ed))

(def ks_pu (c/mk c/K c/S c/_pu))
(def ks_pd (c/mk c/K c/S c/_pd))
(def ks_hm (c/mk c/K c/S c/_hm))
(def ks_ed (c/mk c/K c/S c/_ed))

(def ko_pu (c/mk c/K c/O c/_pu))
(def ko_pd (c/mk c/K c/O c/_pd))
(def ko_hm (c/mk c/K c/O c/_hm))
(def ko_ed (c/mk c/K c/O c/_ed))

(def kos_pu (c/mk c/K c/O c/S c/_pu))
(def kos_pd (c/mk c/K c/O c/S c/_pd))
(def kos_hm (c/mk c/K c/O c/S c/_hm))
(def kos_ed (c/mk c/K c/O c/S c/_ed))

(def kt_pu (c/mk c/K c/T c/_pu))
(def kt_pd (c/mk c/K c/T c/_pd))
(def kt_hm (c/mk c/K c/T c/_hm))
(def kt_ed (c/mk c/K c/T c/_ed))

(def kts_pu (c/mk c/K c/T c/S c/_pu))
(def kts_pd (c/mk c/K c/T c/S c/_pd))
(def kts_hm (c/mk c/K c/T c/S c/_hm))
(def kts_ed (c/mk c/K c/T c/S c/_ed))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
