import React from "react";
import Dropdown from "./Dropdown";

const targetSpecies = [
  { code: "DEMERSAL", name: "Demersal species" },
  { code: "PELAGIC", name: "Pelagic species" },
  { code: "SCALLOP", name: "Scallops" },
  { code: "CRAB", name: "Crabs" },
  { code: "SALMON", name: "Salmon" },
];

function TargetSpeciesGroupDropdown({ formControl, registerName }) {
  return (
    <Dropdown
      label="Target Species Group"
      options={targetSpecies}
      formControl={formControl}
      registerName={registerName}
    />
  );
}

export default TargetSpeciesGroupDropdown;
