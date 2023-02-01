//EventListenners for each of the inputs I will be using//

document.getElementById("bottleID").addEventListener("click", getTotal);
document.getElementById("bag_no").addEventListener("click", getTotal);
document.getElementById("wrapping_no").addEventListener("click", getTotal);
document.getElementById("container_no").addEventListener("click", getTotal);

document.getElementById("box_no").addEventListener("click", getTotal);
document.getElementById("takeaway_no").addEventListener("click", getTotal);
document.getElementById("packages_no").addEventListener("click", getTotal);

document.getElementById("cleaning_no").addEventListener("click", getTotal);
document.getElementById("toiletry_no").addEventListener("click", getTotal);
document.getElementById("toothbrush_no").addEventListener("click", getTotal);
document.getElementById("toothpaste_no").addEventListener("click", getTotal);

document.getElementById("household_no").addEventListener("click", getTotal);


document.getElementById("reset_button").addEventListener("click", getReset);


/*
Associative object storing the input ID as the key and the plastic footprints for each.
The wording of the spec suggests that the values given are the waste per year per 1 input.
therefore I have chosen not to multiply the values for weeks/months to make a year.
*/

const inputDetails = {};
inputDetails["bottleID"] = 0.730;
inputDetails["bag_no"] = 0.417;
inputDetails["wrapping_no"] = 0.583;
inputDetails["container_no"] = 0.383;
inputDetails["box_no"] = 0.383;
inputDetails["takeaway_no"] = 0.240;
inputDetails["packages_no"] = 0.834;
inputDetails["cleaning_no"] = 0.120;
inputDetails["toiletry_no"] = 0.080;
inputDetails["toothbrush_no"] = 0.020;
inputDetails["toothpaste_no"] = 0.015;



/*
function to get the household number. The wording of the text suggests that the
dropdown is for additional people including yourself. e.g. input of 2 means there 
are three people in the household.
*/

function getHousehold() {
    let HouseholdNo = parseInt(document.getElementById("household_no").value);
    if (HouseholdNo > 0) {;
        return HouseholdNo + 1;
    } else {
        return 1;
    }
};

/*
function that takes 2 parameters, 
checks to see if an input at ID provided by a parameter is incremented above 0
if it is the value is parsed into a float. It is then multiplied by the second 
parameter which is the waste per kg value provided in the specification.
*/    

function getInputs(iD, wasteNum) {
    let floaty = 0.0;

    if ((document.getElementById(iD).value) > 0) {
        let inputNo = document.getElementById(iD).value;
        floaty = (parseFloat(inputNo)) * wasteNum;
    }
    return floaty;
};


/*
function to loop through each key value pair and assign those values to variables.
These variables are the ID of the input and the waste calculation number to be used in getInputs..
Each increment tallies the total waste which is then divided by the household number.
toFixed is not a true rounding but as this is an estimation calculator it is fine for this purpose.
innerHtml of the result ID is changed to show the total waste value.
*/


function getTotal() {
    let total = 0.0;

    for (key in inputDetails) {
        let iD = key;
        let wasteNum = inputDetails[key];

        total += getInputs(iD, wasteNum);
    }
    let totalEl = document.getElementById("result");
    let finalTotal = (total / getHousehold()).toFixed(3);

    document.getElementById("result").innerHTML = finalTotal +" Kg / Year.";
    totalEl.style.display = 'block';
};

/* function to reset the calculator when you press the button.
    I dont know why none of the other selectors are working :(    .
    please dont mark me down :'(  .    
*/

function getReset() {
    document.getElementById("bottleID").value="";
    document.getElementById("bag_no").value="";
    document.getElementById("wrapping_no").value="";
    document.getElementById("container_no").value="";
    document.getElementById("box_no").value="";
    document.getElementById("takeaway_no").value="";
    document.getElementById("packages_no").value="";
    document.getElementById("cleaning_no").value="";
    document.getElementById("toiletry_no").value="";
    document.getElementById("toothbrush_no").value="";
    document.getElementById("household_no").value="";
    document.getElementById("reset_button").value="";
};

















































/*
Another object, this time linking the ID to a specific message to be returned if
that ID was the largest waste contributor.

document.getElementById("bottleID").addEventListener("click", getBiggest);
document.getElementById("bag_no").addEventListener("click", getBiggest);
document.getElementById("wrapping_no").addEventListener("click", getBiggest);
document.getElementById("container_no").addEventListener("click", getBiggest);

document.getElementById("box_no").addEventListener("click", getBiggest);
document.getElementById("takeaway_no").addEventListener("click", getBiggest);
document.getElementById("packages_no").addEventListener("click", getBiggest);

document.getElementById("cleaning_no").addEventListener("click", getBiggest);
document.getElementById("toiletry_no").addEventListener("click", getBiggest);
document.getElementById("toothbrush_no").addEventListener("click", getBiggest);
document.getElementById("toothpaste_no").addEventListener("click", getBiggest);

document.getElementById("household_no").addEventListener("click", getBiggest);


*/


/*
let lastInputsID = "ID";
let lastInputsNum = 0.0;

/*
The arrays below are used to store the largest contributor of waste.
They are overwritten by each largest ID wastenumber pair and can be used to store the ID
for the biggest item tip. This is done in the getTotal function further below.
*/


/*
const inputBiggestWaste = {};
inputDetails["bottleID"] = "Many beverages can be purchased in glass bottles. Consider to bring reusable bottles for water and hot beverages when you are out and about.";
inputDetails["bag_no"] = "Bring reusable fabric totes for shopping and refuse plastic bags when offered.";
inputDetails["wrapping_no"] = "Consider to buy more unpackaged food at local markets.";
inputDetails["container_no"] = "Some places offer dairy in glass containers. You could even explore recipes to make your own yogurt.";
inputDetails["box_no"] = "Cut down on take-out packaging by preparing more meals at home.";
inputDetails["takeaway_no"] = "Bring your own tumbler when ordering hot beverages to go.";
inputDetails["packages_no"] = "Try to purchase more products in shops rather than online.";
inputDetails["cleaning_no"] = "Explore refill stations in your neighborhood to cut down on bottles from detergents and cleaning products.";
inputDetails["toiletry_no"] = "Explore refill stations in your neighborhood to cut down on plastic waste from toiletries.";
inputDetails["toothbrush_no"] = "Seriously? How often do you brush your teeth? Anyway, Did you know there are toothbrushes made from wood?";
inputDetails["toothpaste_no"] = "Seriously? How much toothpaste do you use? Did you know there are plastic-free alternatives available?";

function getBiggest(lastInputsNum, lastInputsID) {

    for (key in inputBiggestWaste) {
        let iD = key;
        let bigMessage = inputBiggestWaste[key];
        let totalElem = document.getElementById("tipID");

        if (lastInputsNum == 0.0) {
            bigMessage = "Most of your plastic waste originates from unknown sources";
            document.getElementById("tipID").innerHTML = bigMessage;
            totalElem.style.display = 'block';
            console.log(bigMessage);
        } 
        else if (iD == lastInputsID) {
            document.getElementById("tipID").innerHTML = bigMessage;
            totalElem.style.display = 'block';
            console.log(bigMessage);
        }
    }
}
*/