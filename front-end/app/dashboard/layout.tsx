import UsersTasksHead from "./usersTasksHead"
import HeaderUsuario from "./HeaderUsuario"


export const generateMetadata = UsersTasksHead



export default function DashBoardLayout({children}: {children: React.ReactNode}) {
    return (
        <html lang="pt-br">
            <body>
                <header>
                    <HeaderUsuario/>
                </header>
                {children}
            </body>
        </html>
    )

}