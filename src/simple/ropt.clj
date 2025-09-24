;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.ropt
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "ropt.edn")

(defn ropt []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option Mode"
   :rules
   [;
  ; action glyphs
    [:!E#Pcaps_lock [:caps_lock]]

  ; numeric glyphs
    [:!E#P9 [:!Scomma :!S9]]                                                                         ; '<('
    [:!E#P0 [:!S0 :spacebar]]                                                                        ; ') '

    [:!ER#P9 [:!S4 :!S9]]                                                                            ; '$('

  ; technical glyphs 
    [:!E#Popen_bracket [:!S3 :open_bracket]]                                                         ; '#['
    [:!E#Pclose_bracket [:close_bracket :spacebar]]                                                  ; '] '
    [:!E#Psemicolon [:semicolon :spacebar]]                                                          ; '; '
    [:!E#Pquote [:quote :slash :quote]]                                                              ; ''/''
  ; [:!E#Pbackslash []]
    [:!E#Pcomma [:comma :spacebar]]                                                                  ; ', '
    [:!E#Pperiod [:period :spacebar]]                                                                ; '. '
    [:!E#Pslash [:spacebar :hyphen]]                                                                 ; ' -'

    [:!ER#Popen_bracket [:!S4 :!Sopen_bracket]]                                                      ; '${'
    [:!ER#Pclose_bracket [:!Sclose_bracket :spacebar]]                                               ; '} '
    [:!ER#Psemicolon [:!Ssemicolon :spacebar]]                                                       ; ': '
    [:!ER#Pquote [:!Squote :slash :!Squote]]                                                         ; '"/"'
  ; [:!ER#Pbackslash []]                                                                           ;
    [:!ER#Pcomma [:!Scomma :!Ssemicolon]]                                                            ; '<:'
    [:!ER#Pperiod [:!Speriod :!Ssemicolon]]                                                          ; '>:'
    [:!ER#Pslash [:spacebar :hyphen :hyphen]]                                                        ; ' --'

  ; alphabetic glyphs
  ; [:!E#Pa []]                                                                                    ;
    [:!E#Pb [:b :u :g :!Ssemicolon :spacebar]]                                                       ; 'bug: '
  ; [:!E#Pc []]                                                                                    ;
    [:!E#Pd [:d :o :c :!Ssemicolon :spacebar]]                                                       ; 'doc: '
  ; [:!E#Pe []]                                                                                    ;
    [:!E#Pf [:f :e :a :t :!Ssemicolon :spacebar]]                                                    ; 'feat: '
  ; [:!E#Pg []]                                                                                    ;
  ; [:!E#Ph []]                                                                                    ;
  ; [:!E#Pi []]                                                                                    ;
  ; [:!E#Pj []]                                                                                    ;
  ; [:!E#Pk []]                                                                                    ;
  ; [:!E#Pl []]                                                                                    ;
  ; [:!E#Pm []]                                                                                    ;
  ; [:!E#Pn []]                                                                                    ;
  ; [:!E#Po []]                                                                                    ;
  ; [:!E#Pp []]                                                                                    ;
  ; [:!E#Pq []]                                                                                    ;
    [:!E#Pr [:r :e :f :a :c :t :o :r :!Ssemicolon :spacebar]]                                        ; 'refactor: '
  ; [:!E#Ps []]                                                                                    ;
    [:!E#Pt [:t :e :s :t :!Ssemicolon :spacebar]]                                                    ; 'test: '
  ; [:!E#Pu []]                                                                                    ;
  ; [:!E#Pv []]                                                                                    ;
    [:!E#Pw [:w :i :p :!Ssemicolon :spacebar]]                                                       ; 'wip: '
    [:!E#Px [:f :i :x :!Ssemicolon :spacebar]]                                                       ; 'fix: '
  ; [:!E#Py []]                                                                                    ;
  ; [:!E#Pz []]                                                                                    ;
    [:!E#Pright_control [:!Ssemicolon :i :n :!Ssemicolon]]                                           ; ' ∈ '

  ; [:!ER#Pa []]                                                                                   ;
    [:!ER#Pb [:!Sb :!Su :!Sg :!Ssemicolon :spacebar]]                                                ; 'BUG: '
  ; [:!ER#Pc []]                                                                                   ;
    [:!ER#Pd [:!Sd :!So :!Sc :!Ssemicolon :spacebar]]                                                ; 'DOC: '
  ; [:!ER#Pe []]                                                                                   ;
    [:!ER#Pf [:!Sf :!Se :!Sa :!St :!Ssemicolon :spacebar]]                                           ; 'FEAT: '
  ; [:!ER#Pg []]                                                                                   ;
  ; [:!ER#Ph []]                                                                                   ;
  ; [:!ER#Pi []]                                                                                   ;
  ; [:!ER#Pj []]                                                                                   ;
  ; [:!ER#Pk []]                                                                                   ;
  ; [:!ER#Pl []]                                                                                   ;
  ; [:!ER#Pm []]                                                                                   ;
  ; [:!ER#Pn []]                                                                                   ;
  ; [:!ER#Po []]                                                                                   ;
  ; [:!ER#Pp []]                                                                                   ;
  ; [:!ER#Pq []]                                                                                   ;
    [:!ER#Pr [:!Sr :!Se :!Sf :!Sa :!Sc :!St :!So :!Sr :!Ssemicolon :spacebar]]                       ; 'REFACTOR: '
  ; [:!ER#Ps []]                                                                                   ;
    [:!ER#Pt [:!St :!So :!Sd :!So :!Ssemicolon :spacebar]]                                           ; 'TODO: '
  ; [:!ER#Pu []]                                                                                   ;
  ; [:!ER#Pv []]                                                                                   ;
    [:!ER#Pw [:!Sw :!Si :!Sp :!Ssemicolon :spacebar]]                                                ; 'WIP: '
    [:!ER#Px [:!Sf :!Si :!Sx :!Ssemicolon :spacebar]]                                                ; 'FIX: '
  ; [:!ER#Py []]                                                                                   ;
  ; [:!ER#Pz []]                                                                                   ;
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
