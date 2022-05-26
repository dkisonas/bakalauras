import React from "react";
import Dropdown from "./Dropdown";

const presentation = [
  { name: "HEA with skin on, spine on, tail on", code: "CBF" },
  { name: "Guts and head removed", code: "GUH" },
  { name: "GUT without removing liver parts", code: "GUL" },
  { name: "GUH+SKI", code: "GUS" },
  { name: "All guts removed", code: "GUT" },
  { name: "Heads off", code: "HEA" },
  {
    name: "Transversal cut removing all parts from head to belly",
    code: "JAP",
  },
  { name: "Japanese cut with tail removed", code: "JAT" },
  { name: "Double fillet, HEA, skin+tails+fins ON", code: "LAP" },
  { name: "Liver", code: "LVR" },
  { name: "Liver - collective presentation*", code: "LVR-C" },
  { name: "Claws only", code: "CLA" },
  { name: "Any other presentation", code: "OTH" },
  { name: "Roe(s)", code: "ROE" },
  { name: "Roe(s) - Collective presentation*", code: "ROE-C" },
  {
    name: "Headed with skin on, spine on, tail on and salted dry",
    code: "SAD",
  },
  { name: "CBF+salted", code: "SAL" },
  { name: "GUH+salted", code: "SGH" },
  { name: "GUT+salted", code: "SGT" },
  { name: "Skin off", code: "SKI" },
  { name: "Surimi", code: "SUR" },
  { name: "Tails only", code: "TAL" },
  { name: "Gilled, gutted, part of head off, fins off", code: "DWT" },
  { name: "Tail off", code: "TLD" },
  { name: "Tongue", code: "TNG" },
  { name: "Tongue - collective presentation*", code: "TNG-C" },
  { name: "Tube only (Squid)", code: "TUB" },
  { name: "No processing", code: "WHL" },
  { name: "Wings only", code: "WNG" },
  {
    name: "HEA+GUT+TLD+bones off. Each fish originates two fillets not joined by any par",
    code: "FIL",
  },
  {
    name: "FIL+SKI  Each fish originates two fillets not joined by any par",
    code: "FIS",
  },
  { name: "Filleted with skin and bones on", code: "FSB" },
  { name: "Filleted with skin removed and pinbone on", code: "FSP" },
  { name: "GUH+TLD", code: "GHT" },
  { name: "Guts and gills removed", code: "GUG" },
];

function PresentationDropdown({ formControl, registerName, attrName, index }) {
  return (
    <Dropdown
      options={presentation}
      formControl={formControl}
      attrName={attrName}
      index={index}
      registerName={registerName}
    />
  );
}

export default PresentationDropdown;
