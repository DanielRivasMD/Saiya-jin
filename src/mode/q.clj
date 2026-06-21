;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Q
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.q
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
            [config.function :as f]

            [config.helix :as hx]
            [config.lazygit :as lg]
            [config.micro :as mc]
            [config.serpl :as sr]
            [config.zellij :as zj]
))

(def out-file "q.edn")

(defn q-mode []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Q mode"
   :rules
   [:q-mode
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "pane left",          :exec zj/pane-left}]}         [r/kp_al       [f/kt_f1]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane right",         :exec zj/pane-right}]}        [r/kp_ar       [f/kt_f2]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane up",            :exec zj/pane-up}]}           [r/kp_au       [f/kt_f4]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane down",          :exec zj/pane-down}]}         [r/kp_ad       [f/kt_f5]      c/term]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kp_ob       [f/kos_f7]]
    ^{:doc/actions [{}]}                                                                           [t/kp_cb       [f/kos_f8]]
    ^{:doc/actions [{}]}                                                                           [t/kp_sc       [t/k_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kp_qu       [t/k_qu]]
    ^{:doc/actions [{:program c/zj,    :action "pane split right",   :exec zj/pane-new-right}]}    [t/kp_bl       [f/kt_f8]      c/term]
    ^{:doc/actions [{}]}                                                                           [t/kp_cm       [t/k_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kp_pe       [t/k_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kp_sl       [t/k_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/brv,   :action "vimium new tab foreground"}]}                      [a/kp_db       [f/ko_f1]      c/browser]
    ^{:doc/actions [{:program c/brv,   :action "vimium activate link"}]}                           [a/kp_re       [f/ko_f2]      c/browser]
    ^{:doc/actions [{:program c/brv,   :action "vimium new tab background"}]}                      [a/kp_rs       [f/ko_f3]      c/browser]
    ^{:doc/actions [{:program c/brv,   :action "vimium download link"}]}                           [a/kp_ro       [f/ko_f4]      c/browser]
    ^{:doc/actions [{:program c/brv,   :action "vimium copy url link"}]}                           [a/kp_rc       [f/ko_f5]      c/browser]
    ^{:doc/actions [{:program c/brv,   :action "vimium new tab multiple"}]}                        [a/kp_sp       [f/ko_f6]      c/browser]

    ^{:doc/actions [{:program c/zj,    :action "pane close",         :exec zj/pane-close}]}        [a/kp_db       [f/kt_f6]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin pane picker", :exec zj/pane-picker}]}       [a/kp_re       [f/ks_f5]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane split down",    :exec zj/pane-new-down}]}     [a/kp_rs       [f/kt_f9]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane focus",         :exec zj/pane-focus}]}        [a/kp_ro       [f/kt_f7]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "mode pane rename",   :exec zj/rename-pane-mode}
                    {:program c/zp,    :action "abort pane rename",  :exec zj/p-abort-rename}]}    [a/kp_rc       [f/kt_f10]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "pane jump back",     :exec zj/last-pane}]}         [a/kp_sp       [f/kt_f11]     c/term]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [n/kp_1        [n/k_1]]
    ^{:doc/actions [{}]}                                                                           [n/kp_2        [n/k_2]]
    ^{:doc/actions [{}]}                                                                           [n/kp_3        [n/k_3]]
    ^{:doc/actions [{}]}                                                                           [n/kp_4        [n/k_4]]
    ^{:doc/actions [{}]}                                                                           [n/kp_5        [n/k_5]]
    ^{:doc/actions [{}]}                                                                           [n/kp_6        [n/k_6]]
    ^{:doc/actions [{}]}                                                                           [n/kp_7        [n/k_7]]
    ^{:doc/actions [{}]}                                                                           [n/kp_8        [n/k_8]]
    ^{:doc/actions [{}]}                                                                           [n/kp_9        [n/k_9]]
    ^{:doc/actions [{}]}                                                                           [n/kp_0        [n/k_0]]
    ^{:doc/actions [{}]}                                                                           [n/kp_hy       [n/k_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kp_eq       [n/k_eq]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [b/kp_a        [b/k_a]]
    ^{:doc/actions [{}]}                                                                           [b/kp_b        [b/k_b]]
    ^{:doc/actions [{}]}                                                                           [b/kp_c        [b/k_c]]
    ^{:doc/actions [{}]}                                                                           [b/kp_d        [b/k_d]]
    ^{:doc/actions [{}]}                                                                           [b/kp_e        [b/k_e]]
    ^{:doc/actions [{}]}                                                                           [b/kp_f        [b/k_f]]
    ^{:doc/actions [{}]}                                                                           [b/kp_g        [b/k_g]]
    ^{:doc/actions [{}]}                                                                           [b/kp_h        [b/k_h]]
    ^{:doc/actions [{}]}                                                                           [b/kp_i        [b/k_i]]
    ^{:doc/actions [{}]}                                                                           [b/kp_j        [b/k_j]]
    ^{:doc/actions [{}]}                                                                           [b/kp_k        [b/k_k]]
    ^{:doc/actions [{}]}                                                                           [b/kp_l        [b/k_l]]
    ^{:doc/actions [{}]}                                                                           [b/kp_m        [b/k_m]]
    ^{:doc/actions [{}]}                                                                           [b/kp_n        [b/k_n]]
    ^{:doc/actions [{}]}                                                                           [b/kp_o        [b/k_o]]
    ^{:doc/actions [{}]}                                                                           [b/kp_p        [b/k_p]]
    ^{:doc/actions [{}]}                                                                           [b/kp_q        [b/k_q]]
    ^{:doc/actions [{}]}                                                                           [b/kp_r        [b/k_r]]
    ^{:doc/actions [{}]}                                                                           [b/kp_s        [b/k_s]]
    ^{:doc/actions [{}]}                                                                           [b/kp_t        [b/k_t]]
    ^{:doc/actions [{}]}                                                                           [b/kp_u        [b/k_u]]
    ^{:doc/actions [{}]}                                                                           [b/kp_v        [b/k_v]]
    ^{:doc/actions [{}]}                                                                           [b/kp_w        [b/k_w]]
    ^{:doc/actions [{}]}                                                                           [b/kp_x        [b/k_x]]
    ^{:doc/actions [{}]}                                                                           [b/kp_y        [b/k_y]]
    ^{:doc/actions [{}]}                                                                           [b/kp_z        [b/k_z]]
    ^{:doc/actions [{}]}                                                                           [b/kp_rt       [b/k_rt]]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (q-mode)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
