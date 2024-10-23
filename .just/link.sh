#!/bin/bash
####################################################################################################

# config
source ".config/config.sh"

####################################################################################################

# link
ln -sf "${karabiner}" "${HOME}/.config/"         && echo "Linked ${BIYELLOW}=>${NC} ${BBLUE}karabiner${NC}"
ln -sf "${karabiner}/karabiner.edn" "${config}/" && echo "Linked ${BIYELLOW}=>${NC} ${BBLUE}karabiner${NC}"

####################################################################################################
