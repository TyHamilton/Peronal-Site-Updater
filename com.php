<?php

#stores data for programs array will be filled with class objects
class prog{
private  $iD = -1;
private $name = 'none';
private $img = 'none';
private $clas = array();

#setters
public function __set($property, $value) {
   if (property_exists($this, $property)) {
     $this->$property = $value;
   }
 }

  function aID($a){
    $this->iD = $a;
  }

  function aName($b){
    $this->name = $b;
  }

  function aImg($c){
    $this->img = $c;
  }

  function aClas($d){

    array_push($this->clas, $d);
  }


#getters

public function __get($property){
  if (property_exists($this, $property)) {
      return $this->$property;
    }

}

  function gID(){
    return $this->iD;
  }

  function gName(){
    return $this->name;
  }

  function gIMG(){
    return $this->img;
  }
  function gClass(){
    return $this->clas;
  }

  #functions
  function spam(){}


}

#stores class data for programs these are fend in to prog array
class cla{
private  $cID = -1;
private  $pID = -1;
private  $nameC = 'none';
private  $code = 'none';

#setters
public function __set($property, $value) {
   if (property_exists($this, $property)) {
     $this->$property = $value;

   }
}

function aCiD($a){
  $this->cID = $a;
}

function aPiD($b){
  $this->pID = $b;
}

function aNameC($c){
  $this->nameC = $c;
  // print $this-$nameC;
}

function aCode($d){
  $this->code = $d;
}

#getters
function gCiD(){
  return $this->cID;
}

function gPiD(){
  return $this->pID;
}

function gNameC(){
  return $this->nameC;
}


function gCode(){
  return $this->code;
}

public function __get($property){
  if (property_exists($this, $property)) {
      return $this->$property;
    }

}

}
#primary data store

#dataSite contains program programs objects
 $dataSite = array();
#contains class data
 $dataClass = array();


#SQL code
$link = mysql_connect();

if (!$link) {
   die('Could not connect: ' . mysql_error());
}

mysql_select_db(tyhamilton);
$QUERYPROGRAMS= "SELECT _pID, name, img FROM programs";

$result = mysql_query($QUERYPROGRAMS);
  if(!$result){
    $message  = 'Invalid query: ' . mysql_error() . "\n";
    $message .= 'Whole query: ' . $query;
    die($message);
}

#creates program objects
while ($row = mysql_fetch_assoc($result)) {
    $nPro = new prog;
    $nPro->aID($row['_pID']);
    $nPro->aName($row['name']);
    $nPro->aIMG($row['img']);
    // print $nPro->gName();
    array_push($dataSite,$nPro);

}

#creats class objects
$QUERYCLASS = "SELECT _cID, _pID, name FROM class";
$result1 = mysql_query($QUERYCLASS);

while ($row = mysql_fetch_assoc($result1)) {
    $nCla = new cla;
    $nCla->aCiD($row['_cID']);
    $nCla->aPiD($row['_pID']);
    $checkC = $row['_pID'];
    $nCla->aNameC($row['name']);
    $nameProvider = $row['name'];
    #code comented out for size issues
    #$nCla->aCode($row['code']);
    $init =  count($dataSite);
    // print $nCla->gNameC();
    array_push($dataClass,$nCla);
#loops through programs checking for a match
    for($i = 0; $i < $init; $i ++){
        $checkP = $dataSite[$i];
        $checkID = $checkP->gID();
        // print $i;
        // print $checkID." vs ".$checkC;
        if($checkID==$checkC){
          // print "found"."<br>";
          $checkP->aClas($nameProvider);
        }

    }

}



mysql_free_result($result);
mysql_free_result($result1);

#contains program links
$linkProgram  = array();
#buttons for programs
$ulText = "<ul class= hidUL>";

#contains method to show buttons for classes
$linkClass = array();
#buttons for classes
$ulText1="<ul class= hidUL>";

#contains HTM storage for classes
$linkCode = array();

#method name temp
$methName ="";


#starts display hmtl


// print '<section id="siteDataHTML">';
// print '   <div class="container">';
print '<script language = "javscript" type="text/javascript">';
print '<!-- Hide JavaScript funtions';

// print  " \n var junk =\" Test  \" + \n \"test \" ";

#code for creating the page data this loops through stored data and
# attaches HTML files and creates methods to be called based off names

foreach ($dataSite  as $programTarget ) {
#this loop will focus on program classes and create a method to show class buttons for a program

$programName = $programTarget->gName();
$programID = $programTarget->gID();
// print $programName;

#this button calls a method that will be cretated to show classes for this program
$programButton = "<button class= mainB onclick=$programName();>  $programName  </a>   &nbsp;|&nbsp;</button>";
// print $programButton;

#appends button code together
$ulText = $ulText.$programButton;

#access names of the classes associated with this program
$aRayClass = $programTarget->gClass();

#inner loop cycles through class names and the class array to make buttons
  foreach ($aRayClass as $className) {
    #creates button for classes
    $classButton = "<button class= mainB onclick=$className$programName();> $className </a> &nbsp;|&nbsp;</button>";
    #appends buttons to one UL
    $ulText1 = $ulText1.$classButton;
    #buttons have been created for classes
  }

  #ends UL list for class buttons
  $ulText1 = "$ulText1</ul>";

  #creates method to show all class buttons for program
  $methProgram ="\nfunction $programName(){var el = document.getElementById('navF');el.innerHTML = '$ulText1';}";
  array_push($linkClass,$methProgram);
  // print $methProgram;



  #inner loop two: this creates the method to create methods to show class data
  foreach ($dataClass as $classTarget ) {
    $cName = $classTarget->gNameC();
    $cCode = "code test";
    $iDclas = $classTarget->gCiD();
  //
  // $fir = "<";
  // $sec = "?";
  // $thir = "php";
  // $four = " $";
  // $fift = "_SESSION['codeID']=";
  // $six = ";?";
  // $sev =">";


    $classMeth = "\nfunction $cName$programName(){var cID = $iDclas; phpPull(cID);}";
    array_push($linkCode,$classMeth);
    print $classMeth;


  }
  #clears ul code
  $ulText1 ="<ul>";



#end of program loop
}

  // print $ulText1

foreach ($linkClass as $classTarget) {
print $classTarget;
}
print "\n";

foreach ($linkCode as $codeTarget ) {
print $codeTarget;
}
$ulText = "$ulText</ul>";
print "\nfunction launcherS(){var el = document.getElementById('navP');el.innerHTML = '$ulText';}";



#ends HTML
print "\n-->\n";
print '   </script>';
// print '  </div>';
// print '</section >';


// print $ulText;




?>
