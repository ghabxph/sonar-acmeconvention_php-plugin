<?php

create_function('');                                                      // NOK
eval('');                                                                 // NOK
preg_replace($vuln, $payload, $content);                                  // NOK  - Risky
preg_replace("/{(\w*)}/aeda", '$this->TemplateGlobals["$1"]', $content);  // NOK  - Risky
preg_replace("/{(\w*)}/", '$this->TemplateGlobals["$1"]', $content);      // OK   - Just fine.
