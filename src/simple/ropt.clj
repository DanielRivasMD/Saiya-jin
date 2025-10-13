;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.ropt
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
						))

(def out-file "ropt.edn")

(defn ropt []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [r/kep_al      [r/ke_al]]
    ^{:doc/actions [{}]}                                                                           [r/kep_ar      [r/ke_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kep_au      [r/ke_au]]
    ^{:doc/actions [{}]}                                                                           [r/kep_ad      [r/ke_ad]]

    ^{:doc/actions [{}]}                                                                           [r/kerp_al     [r/ker_al]]
    ^{:doc/actions [{}]}                                                                           [r/kerp_ar     [r/ker_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kerp_au     [r/ker_au]]
    ^{:doc/actions [{}]}                                                                           [r/kerp_ad     [r/ker_ad]]

    ; technical glyphs
    [:!E#Popen_bracket [:!S3 :open_bracket]]                                                         ; '#['
    [:!E#Pclose_bracket [:close_bracket :spacebar]]                                                  ; '] '
    [:!E#Psemicolon [:semicolon :spacebar]]                                                          ; '; '
    [:!E#Pquote [:quote :slash :quote]]                                                              ; ''/''
    ^{:doc/actions [{}]}                                                                           [t/kep_bl      [t/ke_bl]]
    [:!E#Pcomma [:comma :spacebar]]                                                                  ; ', '
    [:!E#Pperiod [:period :spacebar]]                                                                ; '. '
    [:!E#Pslash [:spacebar :hyphen]]                                                                 ; ' -'

    [:!ER#Popen_bracket [:!S4 :!Sopen_bracket]]                                                      ; '${'
    [:!ER#Pclose_bracket [:!Sclose_bracket :spacebar]]                                               ; '} '
    [:!ER#Psemicolon [:!Ssemicolon :spacebar]]                                                       ; ': '
    [:!ER#Pquote [:!Squote :slash :!Squote]]                                                         ; '"/"'
    ^{:doc/actions [{}]}                                                                           [t/kerp_bl     [t/ker_bl]]
    [:!ER#Pcomma [:!Scomma :!Ssemicolon]]                                                            ; '<:'
    [:!ER#Pperiod [:!Speriod :!Ssemicolon]]                                                          ; '>:'
    [:!ER#Pslash [:spacebar :hyphen :hyphen]]                                                        ; ' --'

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [a/kerp_db     [a/ker_db]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_re     [a/ker_re]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_sp     [a/ker_sp]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_lc     [a/ker_lc]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_lo     [a/ker_lo]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_lt     [a/ker_lt]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_rt     [b/ker_rt]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_ls     [a/ker_ls]]
    [:!E#Pcaps_lock [:caps_lock]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_tab    [a/ker_tab]]

    ^{:doc/actions [{}]}                                                                           [a/kerp_db     [a/ker_db]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_re     [a/ker_re]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_sp     [a/ker_sp]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_lc     [a/ker_lc]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_lo     [a/ker_lo]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_lt     [a/ker_lt]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_rt     [b/ker_rt]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_ls     [a/ker_ls]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_esc    [a/ker_esc]]
    ^{:doc/actions [{}]}                                                                           [a/kerp_tab    [a/ker_tab]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/kep_1       [n/ke_1]]
    ^{:doc/actions [{}]}                                                                           [n/kep_2       [n/ke_2]]
    ^{:doc/actions [{}]}                                                                           [n/kep_3       [n/ke_3]]
    ^{:doc/actions [{}]}                                                                           [n/kep_4       [n/ke_4]]
    ^{:doc/actions [{}]}                                                                           [n/kep_5       [n/ke_5]]
    ^{:doc/actions [{}]}                                                                           [n/kep_6       [n/ke_6]]
    ^{:doc/actions [{}]}                                                                           [n/kep_7       [n/ke_7]]
    ^{:doc/actions [{}]}                                                                           [n/kep_8       [n/ke_8]]
    [:!E#P9 [:!Scomma :!S9]]                                                                         ; '<('
    [:!E#P0 [:!S0 :spacebar]]                                                                        ; ') '
    ^{:doc/actions [{}]}                                                                           [n/kep_hy      [n/ke_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kep_eq      [n/ke_eq]]

    ^{:doc/actions [{}]}                                                                           [n/kerp_1      [n/ker_1]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_2      [n/ker_2]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_3      [n/ker_3]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_4      [n/ker_4]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_5      [n/ker_5]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_6      [n/ker_6]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_7      [n/ker_7]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_8      [n/ker_8]]
    [:!ER#P9 [:!S4 :!S9]]                                                                            ; '$('
    ^{:doc/actions [{}]}                                                                           [n/kerp_0      [n/ker_0]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_hy     [n/ker_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kerp_eq     [n/ker_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/kep_a       [b/ke_a]]
    [:!E#Pb [:b :u :g :!Ssemicolon :spacebar]]                                                       ; 'bug: '
    ^{:doc/actions [{}]}                                                                           [b/kep_c       [b/ke_c]]
    [:!E#Pd [:d :o :c :!Ssemicolon :spacebar]]                                                       ; 'doc: '
    ^{:doc/actions [{}]}                                                                           [b/kep_e       [b/ke_e]]
    ^{:doc/actions [{}]}                                                                           [b/kep_f       [b/ke_f]]
    ^{:doc/actions [{}]}                                                                           [b/kep_g       [b/ke_g]]
    ^{:doc/actions [{}]}                                                                           [b/kep_h       [b/ke_h]]
    ^{:doc/actions [{}]}                                                                           [b/kep_i       [b/ke_i]]
    ^{:doc/actions [{}]}                                                                           [b/kep_j       [b/ke_j]]
    ^{:doc/actions [{}]}                                                                           [b/kep_k       [b/ke_k]]
    ^{:doc/actions [{}]}                                                                           [b/kep_l       [b/ke_l]]
    ^{:doc/actions [{}]}                                                                           [b/kep_m       [b/ke_m]]
    ^{:doc/actions [{}]}                                                                           [b/kep_n       [b/ke_n]]
    ^{:doc/actions [{}]}                                                                           [b/kep_o       [b/ke_o]]
    ^{:doc/actions [{}]}                                                                           [b/kep_p       [b/ke_p]]
    ^{:doc/actions [{}]}                                                                           [b/kep_q       [b/ke_q]]
    ^{:doc/actions [{}]}                                                                           [b/kep_r       [b/ke_r]]
    ^{:doc/actions [{}]}                                                                           [b/kep_s       [b/ke_s]]
    [:!E#Pt [:t :e :s :t :!Ssemicolon :spacebar]]                                                    ; 'test: '
    ^{:doc/actions [{}]}                                                                           [b/kep_u       [b/ke_u]]
    ^{:doc/actions [{}]}                                                                           [b/kep_v       [b/ke_v]]
    [:!E#Pw [:w :i :p :!Ssemicolon :spacebar]]                                                       ; 'wip: '
    [:!E#Px [:f :i :x :!Ssemicolon :spacebar]]                                                       ; 'fix: '
    ^{:doc/actions [{}]}                                                                           [b/kep_y       [b/ke_y]]
    ^{:doc/actions [{}]}                                                                           [b/kep_z       [b/ke_z]]
    [:!E#Pright_control [:!Ssemicolon :i :n :!Ssemicolon]]                                           ; ' ∈ '

    ^{:doc/actions [{}]}                                                                           [b/kerp_a      [b/ker_a]]
    [:!ER#Pb [:!Sb :!Su :!Sg :!Ssemicolon :spacebar]]                                                ; 'BUG: '
    ^{:doc/actions [{}]}                                                                           [b/kerp_c      [b/ker_c]]
    [:!ER#Pd [:!Sd :!So :!Sc :!Ssemicolon :spacebar]]                                                ; 'DOC: '
    ^{:doc/actions [{}]}                                                                           [b/kerp_e      [b/ker_e]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_f      [b/ker_f]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_g      [b/ker_g]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_h      [b/ker_h]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_i      [b/ker_i]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_j      [b/ker_j]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_k      [b/ker_k]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_l      [b/ker_l]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_m      [b/ker_m]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_n      [b/ker_n]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_o      [b/ker_o]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_p      [b/ker_p]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_q      [b/ker_q]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_r      [b/ker_r]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_s      [b/ker_s]]
    [:!ER#Pt [:!St :!So :!Sd :!So :!Ssemicolon :spacebar]]                                           ; 'TODO: '
    ^{:doc/actions [{}]}                                                                           [b/kerp_u      [b/ker_u]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_v      [b/ker_v]]
    [:!ER#Pw [:!Sw :!Si :!Sp :!Ssemicolon :spacebar]]                                                ; 'WIP: '
    [:!ER#Px [:!Sf :!Si :!Sx :!Ssemicolon :spacebar]]                                                ; 'FIX: '
    ^{:doc/actions [{}]}                                                                           [b/kerp_y      [b/ker_y]]
    ^{:doc/actions [{}]}                                                                           [b/kerp_z      [b/ker_z]]
    [:!ER#Pright_control [:!Ssemicolon :period :i :n :!Ssemicolon]]                                  ; ' .∈ '
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (ropt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
