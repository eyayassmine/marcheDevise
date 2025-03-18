import React from "react";
import usePanelResizer from "../../hooks/UsePanelResizer"; // Import the custom hook

import "./Home.css"
// Import CSS file
import Devises from "../devise/Devise";
import PositionPannel from "../positionPannel/PositionPanel";

function Home() {
/**      <h1>Welcome to BFITrading</h1>
      <p>Where Knowledge Shapes Civilizations. Explore Your Destiny.</p> */
/*      <Devises />  */
  // Use the hook for the vertical resizer (top/bottom panel)
  const { panelSize: topPanelHeight, onMouseDown: onTopPanelMouseDown } = usePanelResizer(window.innerHeight / 2, "vertical");

  // Calculate the bottom panel height based on the top panel height
  const bottomPanelHeight = window.innerHeight - topPanelHeight;

  return (
    <div className="Home"style={{ display: "flex", flexDirection: "column", height: "100vh" }}>
    {/* Top Panel */}
    <div
      className="top-panel"
      style={{ height: `${topPanelHeight}px` }}
    >
      <Devises />
      </div>

    {/* Resizable Splitter (Vertical) */}
    <div
      className="home-splitter"
      onMouseDown={onTopPanelMouseDown}
    />

    {/* Bottom Panel */}
    <div
      className="bottom-panel"
      style={{
        flexGrow: 1,
        height: `${bottomPanelHeight}px`, // Dynamically adjust the height of the bottom panel
      }}
    >
      <PositionPannel />
      </div>
    </div>


);
}

export default Home;
