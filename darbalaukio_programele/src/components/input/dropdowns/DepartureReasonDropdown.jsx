import React from "react";
import Dropdown from "./Dropdown";

function DepartureReasonDropdown({formControl, registerName}) {

  const reasons = [
    { name: "Fishing", code: "FIS" },
    { name: "Crusing or transit", code: "CRU" },
    { name: "Testing engines", code: "TST" },
    { name: "Guardship duty", code: "GUD" },
    { name: "Scientific research", code: "SCR" },
    { name: "Other", code: "OTH" },
    { name: "Steaming", code: "STE" },
  ];

  return (
    <Dropdown
      label="Departure reason"
      options={reasons}
      formControl={formControl}
      registerName={registerName}
    />
  );
}

export default DepartureReasonDropdown;
