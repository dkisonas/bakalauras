import React from "react";
import Dropdown from "./Dropdown";

const gear = [
  { name: "Towed dredges", code: "DRB" },
  { name: "Hand dredges", code: "DRH" },
  { name: "Mechanized dredges", code: "DRM" },
  { name: "Dredges (nei)", code: "DRX" },
  { name: "Cast nets", code: "FCN" },
  { name: "Cover pots/Lantern nets", code: "FCO" },
  { name: "Falling gear (nei)", code: "FG" },
  { name: "Gillnets and entangling nets (nei)", code: "GEN" },
  { name: "Gillnets (nei)", code: "GN" },
  { name: "Encircling gillnets", code: "GNC" },
  { name: "Drift gillnets", code: "GND" },
  { name: "Fixed gillnets (on stakes)", code: "GNF" },
  { name: "Set gillnets (anchored)", code: "GNS" },
  { name: "Combined gillnets-trammel nets", code: "GTN" },
  { name: "Trammel nets", code: "GTR" },
  { name: "Mechanized lines and pole-and-lines", code: "LHM" },
  { name: "Handlines and hand-operated pole-and-lines", code: "LHP" },
  { name: "Longlines (nei)", code: "LL" },
  { name: "Drifting longlines", code: "LLD" },
  { name: "Set longlines", code: "LLS" },
  { name: "Trolling lines", code: "LTL" },
  { name: "Vertical lines", code: "LVT" },
  { name: "Hooks and lines (nei)", code: "LX" },
  { name: "Lift nets (nei)", code: "LN" },
  { name: "Boat-operated lift nets", code: "LNB" },
  { name: "Portable lift nets", code: "LNP" },
  { name: "Shore-operated stationary lift nets", code: "LNS" },
  { name: "Harpoons", code: "HAR" },
  { name: "Harvesting machines (nei)", code: "HMX" },
  { name: "Drive-in nets", code: "MDR" },
  { name: "Diving", code: "MDV" },
  { name: "Electric fishing", code: "MEL" },
  {
    name: "Hand implements (Wrenching gear, Clamps, Tongs, Rakes, Spears)",
    code: "MHI",
  },
  { name: "Gear nei", code: "MIS" },
  { name: "Pumps", code: "MPM" },
  { name: "Pushnets", code: "MPN" },
  { name: "Scoopnets", code: "MSP" },
  { name: "GEAR NOT KNOW", code: "NK" },
  { name: "RECREATIONAL FISHING GEAR", code: "RG" },
  { name: "No gear", code: "NO" },
  { name: "Beach seines", code: "SB" },
  { name: "- Danish seines", code: "SDN" },
  { name: "- pair seines", code: "SPR" },
  { name: "- Scottish seines", code: "SSC" },
  { name: "Boat seines", code: "SV" },
  { name: "Seine nets (nei)", code: "SX" },
  { name: "Surrounding nets without purse lines", code: "LA" },
  { name: "Purse seines", code: "PS" },
  { name: "- one boat operated purse seines", code: "PS1" },
  { name: "- two boats operated purse seines", code: "PS2" },
  { name: "Surrounding nets (nei)", code: "SUX" },
  { name: "Aerial traps", code: "FAR" },
  { name: "Traps (not specified)", code: "FIX" },
  { name: "Stationary uncovered pound nets", code: "FPN" },
  { name: "Pots", code: "FPO" },
  { name: "Stow nets", code: "FSN" },
  { name: "Barriers, fences, weirs, etc.", code: "FWR" },
  { name: "Fyke nets", code: "FYK" },
  { name: "Otter trawls (nei)", code: "OT" },
  { name: "Single boat bottom otter trawls", code: "OTB" },
  { name: "Single boat midwater otter trawls", code: "OTM" },
  { name: "Multiple bottom otter trawls", code: "OTP" },
  { name: "Twin bottom otter trawls", code: "OTT" },
  { name: "Pair trawls (nei)", code: "PT" },
  { name: "Bottom pair trawls", code: "PTB" },
  { name: "Midwater pair trawls", code: "PTM" },
  { name: "Bottom trawls - electric beam trawls (Pulse Beam)", code: "PUK" },
  { name: "Bottom trawls - electric sumwing trawls (Pulse Wing)", code: "PUL" },
  { name: "Bottom trawls (nei)", code: "TB" },
  { name: "Beam trawls", code: "TBB" },
  { name: "Bottom trawls nephrops trawls", code: "TBN" },
  { name: "Bottom trawls shrimp trawls", code: "TBS" },
  { name: "Midwater trawls (nei)", code: "TM" },
  { name: "Midwater shrimp trawls", code: "TMS" },
  { name: "Semipelagic trawls", code: "TSP" },
  { name: "Trawls (nei)", code: "TX" },
];

function GearDropdown({ formControl, registerName, attrName, index }) {
  return (
    <Dropdown
      options={gear}
      formControl={formControl}
      attrName={attrName}
      index={index}
      registerName={registerName}
    />
  );
}

export default GearDropdown;
