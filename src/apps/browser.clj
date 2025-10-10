;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; browser
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns apps.browser
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]))

(def out-file "browser.edn")

(defn browser []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; TODO: update config
; browser
  {:des "Browser overwrite Arc"

   :rules
   [;
    [:#Pright_shift :right_shift [:browser] {:alone [:!Of1]}]                    ; focus             ;

    [:!EWQ#Pdelete_or_backspace :!Of2 [:browser]]                                ; copy link         ;
    [:!EWQ#Pright_option :!Of3 [:browser]]                                       ; down link         ;

    [:!EWQ#Preturn_or_enter :!Of4 [:browser]]                                   ; current tab       ;

    [:!EWQ#Pright_shift :!Of5 [:browser]]                                       ; new tab           ;
    [:!EWQ#Pright_command :!Of6 [:browser]]                                     ; multitab          ;

    [:!EWQ#Pspacebar :!Of7 [:browser]]                                   ;

    [:!OC#Pspacebar [:!Of8] [:browser]]                                                  ;

    [:!R#Pup_arrow [:!Of9] [:browser]]                                                  ;
    [:!R#Pdown_arrow [:!Of10] [:browser]]                                                  ;

    [:!EWQ#Pup_arrow :!Of11 [:browser]]                                   ;
    [:!EWQ#Pdown_arrow :!Of12 [:browser]]                                   ;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; browser
    [:!T#Pspacebar [:!Cs] [:browser]]                                            ; sidebar
    [:!C#Preturn_or_enter [:!Co] [:browser]]                                     ; fuse

    [:!OC#P1 [:!T1] [:browser]]                                                  ; work space        ;
    [:!OC#P2 [:!T2] [:browser]]                                                  ; work space        ;
    [:!OC#P3 [:!T3] [:browser]]                                                  ; work space        ;
    [:!OC#P4 [:!T4] [:browser]]                                                  ; work space        ;
    [:!OC#P5 [:!T5] [:browser]]                                                  ; work space        ;

    [:!OC#Pkeypad_1 [:!T1] [:browser]]                                           ; work space        ;
    [:!OC#Pkeypad_2 [:!T2] [:browser]]                                           ; work space        ;
    [:!OC#Pkeypad_3 [:!T3] [:browser]]                                           ; work space        ;
    [:!OC#Pkeypad_4 [:!T4] [:browser]]                                           ; work space        ;
    [:!OC#Pkeypad_5 [:!T5] [:browser]]                                           ; work space        ;

    [:!OC#Pdelete_or_backspace [:!Cw] [:browser]]                                           ; close tab
    [:!OC#Preturn_or_enter [:!OCn] [:browser]]                                   ; little arc        ;
    [:!OC#Pright_shift [:!Ct] [:browser]]                              ; new tab
    [:!OC#Pright_command [:!STCr] [:browser]]                                           ; rename
    [:!OC#Pright_option [:!SCt] [:browser]]                                                  ; restore tab

    [:!OT#Pdelete_or_backspace [:!TShyphen] [:browser]]                          ; close split       ;
    [:!OT#Pright_shift [:!TSequal_sign] [:browser]]                              ; new split         ;
    [:!OT#Pright_option [:!STCv] [:browser]]                                           ; segregate split
    [:!OT#Pleft_arrow [:!TSopen_bracket] [:browser]]                             ; left split        ;
    [:!OT#Pright_arrow [:!TSclose_bracket] [:browser]]                           ; right split       ;

    [:!OTC#Pdelete_or_backspace [:!SCk] [:browser]]                                ; clean unpinned
    [:!OTC#Pspacebar [:!Cd] [:browser]]                                    ; pin
    [:!OTC#Preturn_or_enter [:!Cn] [:browser]]                                          ; new duplicate window            ;

    [:!EWQ#Pleft_arrow [:!Copen_bracket] [:browser]]                             ; backward          ;
    [:!EWQ#Pright_arrow [:!Cclose_bracket] [:browser]]                           ; forward           ;

    [:!TC#Pdelete_or_backspace [:!SCw] [:browser]]                                ; close window
    [:!TC#Preturn_or_enter [:!TCn] [:browser]]                                          ; new window            ;
    [:!TC#Pright_shift [:!Cy] [:browser]]                           ; history
    [:!TC#Pright_option [:!SCj] [:browser]]                           ; downloads
    [:!TC#Pright_command [:!STCa] [:browser]]                                           ; archives
    [:!TC#Pspacebar [:!STs] [:browser]]                                    ; spaces

  ; [:!OS#Pd [:!OSd] [:browser]]                                                 ; duplicate         ;

    [:!O#Pright_control [:!Cl] [:browser]]                                                  ; cmd bar

    [:!W#Pright_shift [:!Cf] [:browser]]                                                  ; find
    [:!W#Pspacebar [:!Cj] [:browser]]                                                  ; jump
    [:!O#Ph [:!Cg] [:browser]]                                                  ; find next
    [:!O#Pl [:!SCg] [:browser]]                                                  ; find prev

  ; [:!O#Pr [:!Cr] [:browser]]                                    ; refresh
  ; [:!O#Pt [:!SCd] [:browser]]                                    ; toolbar
    [:!O#Pc [:!SCc] [:browser]]                                                  ; copy url
    [:!SO#Pc [:!SOCc] [:browser]]                                                  ; copy url markdown

  ; [:!C#P0 [:!C9] [:browser]]                                                  ; go last tab
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (browser)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
