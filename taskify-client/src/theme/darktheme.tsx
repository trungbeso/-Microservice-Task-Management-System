import { createTheme } from "@mui/material";

export const darktheme = createTheme({
    palette: {
        mode: "dark",
        background: {
            default: "#0c071b"
        },
        text: {
            primary: "#fff"
        },
        primary: {
            main: "rgba(215,209,215,0.51)"
        }
    }
})