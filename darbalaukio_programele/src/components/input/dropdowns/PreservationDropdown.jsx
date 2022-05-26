import React from "react";
import Dropdown from "./Dropdown";

const preservation = [
  { name: "Alive", code: "ALI" },
  { name: "Boiled", code: "BOI" },
  { name: "Dried", code: "DRI" },
  { name: "Fresh", code: "FRE" },
  { name: "Frozen", code: "FRO" },
  { name: "Salted", code: "SAL" },
  { name: "Smoked", code: "SMO" },
];

function PreservationDropdown({ formControl, registerName, attrName, index }) {
  return (
    <Dropdown
      options={preservation}
      formControl={formControl}
      attrName={attrName}
      index={index}
      registerName={registerName}
    />
  );
}

export default PreservationDropdown;
