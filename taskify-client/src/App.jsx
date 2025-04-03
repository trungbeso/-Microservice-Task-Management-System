import {ThemeProvider} from "@mui/material";
import {darktheme} from "./theme/darktheme.js";

function App() {

  return (
    <ThemeProvider theme={darktheme}>
      <h4 className="text-copy-primary" >Hello this is React</h4>
    </ThemeProvider>
  )
}

export default App
