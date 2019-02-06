<?php

create_function('');                                                      // NOK
eval('');                                                                 // NOK
preg_replace("/{(\w*)}/aeda", '$this->TemplateGlobals["$1"]', $content);  // NOK
preg_replace("/{(\w*)}/", '$this->TemplateGlobals["$1"]', $content);      // OK
