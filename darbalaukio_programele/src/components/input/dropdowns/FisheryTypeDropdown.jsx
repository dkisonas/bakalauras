import React from "react";
import Dropdown from "./Dropdown";
const fisheryTypes = [
  {
    code: "SPELF",
    name: "Small pelagic fisheries (i.e. fisheries for mackerel, herring, horse mackerel, blue whiting, boarfish, anchovy, argentine, sardine, sprat)",
  },
  {
    code: "NWCOD",
    name: "Fisheries for cod, haddock, whiting, saithe in the North Western Waters",
  },
  {
    code: "NWLOB",
    name: "Fisheries for Norway lobster in the North Western Waters",
  },
  {
    code: "NWSPL",
    name: "Fisheries for common sole and plaice in the North Western Waters",
  },
  {
    code: "NWHKE",
    name: "Fisheries for hake in the North Western Waters",
  },
  {
    code: "SWLOB",
    name: "Fisheries for Norway lobster in the South Western Waters",
  },
  {
    code: "SWSPL",
    name: "Fisheries for common sole and plaice in the South Western Waters",
  },
  {
    code: "SWHKE",
    name: "Fisheries for hake in the South Western Waters",
  },
  {
    code: "OTHCL",
    name: "Other fisheries for species subject to catch limits.",
  },
  {
    code: "MEDBL",
    name: "All other species in fisheries not covered by point (art. 15.1.a) in the Mediterranean, in the Black Sea",
  },
  {
    code: "LPELF",
    name: "Large pelagic fisheries (i.e. fisheries for bluefin tuna, swordfish, albacore tuna, bigeye tuna, blue and white marlin);",
  },
  {
    code: "INDUS",
    name: "Fisheries for industrial purposes (inter alia, fisheries for capelin, sandeel and Norwegian pout);",
  },
  { code: "BASAL", name: "Fisheries for salmon in the Baltic Sea." },
  {
    code: "NSCOD",
    name: "Fisheries for cod, haddock, whiting, saithe in the North Sea",
  },
  {
    code: "NSLOB",
    name: "Fisheries for Norway lobster in the North Sea",
  },
  {
    code: "NSSPL",
    name: "Fisheries for common sole and plaice in the North Sea",
  },
  { code: "NSHKE", name: "Fisheries for hake in the North Sea" },
  {
    code: "NSNPR",
    name: "Fisheries for Northern prawn in the North Sea",
  },
];
function FisheryTypeDropdown({formControl, registerName}) {
  return (
    <Dropdown
      label="Fishery type"
      options={fisheryTypes}
      formControl={formControl}
      registerName={registerName}
    />
  );
}

export default FisheryTypeDropdown;
