;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lcmd.edn")

(defn lcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Command Mode"
   :rules
   [;
  ; arrow glyphs
    ^{:doc/actions [{:action "goto line start", :exec ["goto_line_start"],                  :program "helix-common"}
                    {:action "goto line start", :exec ["StartOfLine"],                      :program "micro"}]} [:!C#Pleft_arrow  [:!Ta]  [:term]]
    ^{:doc/actions [{:action "goto line end",   :exec ["goto_line_end", "move_char_right"], :program "helix-common"}
                    {:action "goto line end",   :exec ["EndOfLine"],                        :program "micro"}]} [:!C#Pright_arrow [:!Te]  [:term]]
    ^{:doc/actions [{:action "goto file start", :exec ["goto_file_start"],                  :program "helix-common"}
                    {:action "goto top",        :exec ["gotoTop"],                          :program "lazygit"}
                    {:action "goto file start", :exec ["CursorStart"],                      :program "micro"}]} [:!C#Pup_arrow    [:home] [:term]]
    ^{:doc/actions [{:action "goto file end",   :exec ["goto_last_line"],                   :program "helix-common"}
                    {:action "goto bottom",     :exec ["gotoBottom"],                       :program "lazygit"}
                    {:action "goto file end",   :exec ["CursorEnd"],                        :program "micro"}]} [:!C#Pdown_arrow  [:end]  [:term]]

    ^{:doc/actions [{:action "select line start", :exec ["select_mode", "goto_line_start", "MODE"], :program "helix-common"}
                    {:action "select line start", :exec ["SelectToStartOfLine"],                    :program "micro"}]} [:!CS#Pleft_arrow  [:!Oleft_arrow]  [:term]]
    ^{:doc/actions [{:action "select line end",   :exec ["select_mode", "goto_line_end", "MODE"],   :program "helix-common"}
                    {:action "select line end",   :exec ["SelectToEndOfLine"],                      :program "micro"}]} [:!CS#Pright_arrow [:!Oright_arrow] [:term]]
    ^{:doc/actions [{:action "select file start", :exec ["extend_to_file_start"],                   :program "helix-common"}
                    {:action "select file start", :exec ["SelectToStart"],                          :program "micro"}]} [:!CS#Pup_arrow    [:!Oup_arrow]    [:term]]
    ^{:doc/actions [{:action "select file end",   :exec ["extend_to_file_end"],                     :program "helix-common"}
                    {:action "select file end",   :exec ["SelectToEnd"],                            :program "micro"}]} [:!CS#Pdown_arrow  [:!Odown_arrow]  [:term]]

  ; technical glyphs
    ^{:doc/actions [{}]} [:!C#Popen_bracket   [:!Copen_bracket]]
    ^{:doc/actions [{}]} [:!C#Pclose_bracket  [:!Cclose_bracket]]
    ^{:doc/actions [{}]} [:!C#Psemicolon      [:!Csemicolon]]
    ^{:doc/actions [{:action "", :sequence "`::<`",  :program ""}]} [:!C#Pquote  [:!Ssemicolon :!Ssemicolon :!Scomma]]
    ^{:doc/actions [{}]} [:!C#Pbackslash      [:!Cbackslash]]
    ^{:doc/actions [{:action "", :sequence "' <- '", :program ""}]} [:!C#Pcomma  [:spacebar :!Scomma :hyphen :spacebar]]
    ^{:doc/actions [{:action "", :sequence "` -> `", :program ""}]} [:!C#Pperiod [:spacebar :hyphen :!Speriod :spacebar]]
    ^{:doc/actions [{:action "", :sequence "`//! `", :program ""}]} [:!C#Pslash  [:slash :slash :!S1 :spacebar]]

    ^{:doc/actions [{}]} [:!CS#Popen_bracket  [:!CSopen_bracket]]
    ^{:doc/actions [{}]} [:!CS#Pclose_bracket [:!CSclose_bracket]]
    ^{:doc/actions [{}]} [:!CS#Psemicolon     [:!CSsemicolon]]
    ^{:doc/actions [{}]} [:!CS#Pquote         [:!CSquote]]
    ^{:doc/actions [{}]} [:!CS#Pbackslash     [:!CSbackslash]]
    ^{:doc/actions [{:action "", :sequence "` <<- `", :program ""}]} [:!CS#Pcomma [:spacebar :!Scomma :!Scomma :hyphen :spacebar]]
    ^{:doc/actions [{:action "", :sequence "` ->> `", :program ""}]} [:!CS#Pperiod [:spacebar :hyphen :!Speriod :!Speriod :spacebar]]
    ^{:doc/actions [{:action "", :sequence "`#!/`", :program ""}]} [:!CS#Pslash [:!S3 :!S1 :slash]]

  ; action glyphs
    ^{:doc/actions [{}]} [:!C#Pdelete_or_backspace [:!Cdelete_or_backspace]]
    ^{:doc/actions [{}]} [:!C#Preturn_or_enter     [:!Creturn_or_enter]]
    ^{:doc/actions [{:action "", :sequence "` != `", :program ""}]} [:!C#Pright_shift [:spacebar :!S1 :equal_sign :spacebar]]
    ^{:doc/actions [{:action "", :sequence "` *= `", :program ""}]} [:!C#Pright_option [:spacebar :!S8 :equal_sign :spacebar]]
    ^{:doc/actions [{:action "", :sequence "` /= `", :program ""}]} [:!C#Pright_command [:spacebar :slash :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [:!C#Pspacebar            [:!Cspacebar]]

    ^{:doc/actions [{}]} [:!CS#Pdelete_or_backspace [:!CSdelete_or_backspace]]
    ^{:doc/actions [{:action "", :sequence "`; `", :program ""}]} [:!CS#Preturn_or_enter [:spacebar :!Ssemicolon :equal_sign :spacebar]]
    ^{:doc/actions [{:action "", :sequence "` .!= `", :program ""}]} [:!CS#Pright_shift [:spacebar :period :!S1 :equal_sign :spacebar]]
    ^{:doc/actions [{:action "", :sequence "` .*= `", :program ""}]} [:!CS#Pright_option [:spacebar :period :!S8 :equal_sign :spacebar]]
    ^{:doc/actions [{:action "", :sequence "` ./= `", :program ""}]} [:!CS#Pright_command [:spacebar :period :slash :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [:!CS#Pspacebar            [:!CSspacebar]]

  ; numeric glyphs
    ^{:doc/actions [{}]} [:!C#P1 [:!C1]]
    ^{:doc/actions [{}]} [:!C#P2 [:!C2]]
    ^{:doc/actions [{}]} [:!C#P3 [:!C3]]
    ^{:doc/actions [{}]} [:!C#P4 [:!C4]]
    ^{:doc/actions [{}]} [:!C#P5 [:!C5]]
    ^{:doc/actions [{}]} [:!C#P6 [:!C6]]
    ^{:doc/actions [{}]} [:!C#P7 [:!C7]]
    ^{:doc/actions [{}]} [:!C#P8 [:!C8]]
    ^{:doc/actions [{:action "", :sequence ".(", :program ""}]} [:!C#P9 [:period :!S9]]
    ^{:doc/actions [{}]} [:!C#P0 [:!C0]]
    ^{:doc/actions [{:action "", :sequence "` -= `", :program ""}]} [:!C#Phyphen [:spacebar :hyphen :equal_sign :spacebar]]
    ^{:doc/actions [{:action "", :sequence "` += `", :program ""}]} [:!C#Pequal_sign [:spacebar :!Sequal_sign :equal_sign :spacebar]]

    ^{:doc/actions [{}]} [:!CS#P1 [:!CS1]]
    ^{:doc/actions [{}]} [:!CS#P2 [:!CS2]]
    ^{:doc/actions [{}]} [:!CS#P3 [:!CS3]]
    ^{:doc/actions [{}]} [:!CS#P4 [:!CS4]]
    ^{:doc/actions [{}]} [:!CS#P5 [:!CS5]]
    ^{:doc/actions [{}]} [:!CS#P6 [:!CS6]]
    ^{:doc/actions [{}]} [:!CS#P7 [:!CS7]]
    ^{:doc/actions [{}]} [:!CS#P8 [:!CS8]]
    ^{:doc/actions [{:action "", :sequence "`:(`", :program ""}]} [:!CS#P9 [:!Ssemicolon :!S9]]
    ^{:doc/actions [{}]} [:!CS#P0 [:!CS0]]
    ^{:doc/actions [{:action "", :sequence "` .-= `", :program ""}]} [:!CS#Phyphen [:spacebar :period :hyphen :equal_sign :spacebar]]
    ^{:doc/actions [{:action "", :sequence "` .+= `", :program ""}]} [:!CS#Pequal_sign [:spacebar :period :!Sequal_sign :equal_sign :spacebar]]

  ; alphabetic glyphs
    ^{:doc/actions [{}]} [:!C#Pa [:!Ca]]
    ^{:doc/actions [{}]} [:!C#Pb [:!Cb]]
    ^{:doc/actions [{}]} [:!C#Pc [:!Cc]]
    ^{:doc/actions [{}]} [:!C#Pd [:!Cd]]
    ^{:doc/actions [{}]} [:!C#Pe [:!Ce]]
    ^{:doc/actions [{}]} [:!C#Pf [:!Cf]]
    ^{:doc/actions [{}]} [:!C#Pg [:!Cg]]
    ^{:doc/actions [{}]} [:!C#Ph [:!Ch]]
    ^{:doc/actions [{}]} [:!C#Pi [:!Ci]]
    ^{:doc/actions [{}]} [:!C#Pj [:!Cj]]
    ^{:doc/actions [{}]} [:!C#Pk [:!Ck]]
    ^{:doc/actions [{}]} [:!C#Pl [:!Cl]]
    ^{:doc/actions [{}]} [:!C#Pm [:!Cm]]
    ^{:doc/actions [{}]} [:!C#Pn [:!Cn]]
    ^{:doc/actions [{}]} [:!C#Po [:!Co]]
    ^{:doc/actions [{}]} [:!C#Pp [:!Cp]]
    ^{:doc/actions [{}]} [:!C#Pq [:!Cq]]
    ^{:doc/actions [{}]} [:!C#Pr [:!Cr]]
    ^{:doc/actions [{}]} [:!C#Ps [:!Cs]]
    ^{:doc/actions [{}]} [:!C#Pt [:!Ct]]
    ^{:doc/actions [{}]} [:!C#Pu [:!Cu]]
    ^{:doc/actions [{}]} [:!C#Pv [:!Cv]]
    ^{:doc/actions [{}]} [:!C#Pw [:!Cw]]
    ^{:doc/actions [{}]} [:!C#Px [:!Cx]]
    ^{:doc/actions [{}]} [:!C#Py [:!Cy]]
    ^{:doc/actions [{}]} [:!C#Pz [:!Cz]]
    ^{:doc/actions [{}]} [:!C#Pright_control [:!Cright_control]]

    ^{:doc/actions [{}]} [:!CS#Pa [:!CSa]]
    ^{:doc/actions [{}]} [:!CS#Pb [:!CSb]]
    ^{:doc/actions [{}]} [:!CS#Pc [:!CSc]]
    ^{:doc/actions [{}]} [:!CS#Pd [:!CSd]]
    ^{:doc/actions [{}]} [:!CS#Pe [:!CSe]]
    ^{:doc/actions [{}]} [:!CS#Pf [:!CSf]]
    ^{:doc/actions [{}]} [:!CS#Pg [:!CSg]]
    ^{:doc/actions [{}]} [:!CS#Ph [:!CSh]]
    ^{:doc/actions [{}]} [:!CS#Pi [:!CSi]]
    ^{:doc/actions [{}]} [:!CS#Pj [:!CSj]]
    ^{:doc/actions [{}]} [:!CS#Pk [:!CSk]]
    ^{:doc/actions [{}]} [:!CS#Pl [:!CSl]]
    ^{:doc/actions [{}]} [:!CS#Pm [:!CSm]]
    ^{:doc/actions [{}]} [:!CS#Pn [:!CSn]]
    ^{:doc/actions [{}]} [:!CS#Po [:!CSo]]
    ^{:doc/actions [{}]} [:!CS#Pp [:!CSp]]
    ^{:doc/actions [{}]} [:!CS#Pq [:!CSq]]
    ^{:doc/actions [{}]} [:!CS#Pr [:!CSr]]
    ^{:doc/actions [{}]} [:!CS#Ps [:!CSs]]
    ^{:doc/actions [{}]} [:!CS#Pt [:!CSt]]
    ^{:doc/actions [{}]} [:!CS#Pu [:!CSu]]
    ^{:doc/actions [{}]} [:!CS#Pv [:!CSv]]
    ^{:doc/actions [{}]} [:!CS#Pw [:!CSw]]
    ^{:doc/actions [{}]} [:!CS#Px [:!CSx]]
    ^{:doc/actions [{}]} [:!CS#Py [:!CSy]]
    ^{:doc/actions [{}]} [:!CS#Pz [:!CSz]]
    ^{:doc/actions [{}]} [:!CS#Pright_control [:!CSright_control]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
