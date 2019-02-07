<?php

for ($i = 0; $i < 10; $i++) echo $i;
for ($i = 0; $i < 10; $i++) { // NOK
}
for ($i = 0; $i < 10; $i++) { // OK
    echo $i;
}

foreach ($aFiles as $sFile) echo $sFile;
foreach ($aFiles as $sFile) { // NOK
}
foreach ($aFiles as $sFile) {
    echo $sFile;
}

while($condition) echo "while";
while($condition) { // NOK
}
while($condition) { // OK
    echo "While statement";
}

do {} while($condition);                            // NOK
do { echo "do-while statement"; } while($condition); // OK

if ($condition) echo "if";
else if ($condition) echo "else if";
elseif ($condition) echo "elseif";
else echo "else";

if ($condition) {                   // NOK
} else if ($condition) {            // NOK
} elseif ($condition) {             // NOK
} else {                            // NOK
}

if ($condition) {                   // OK
    echo "I am if statement";
} else if ($condition) {            // OK
    echo "I am else if statement";
} elseif ($condition) {             // OK
    echo "I am elseif statement";
} else {                            // OK
    echo "I am else statement";
}
