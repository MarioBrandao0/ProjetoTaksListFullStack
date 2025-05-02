import UsersTasksHead from "./usersTasksHead"
import HeaderUsuario from "./HeaderDashboard"

export const generateMetadata = UsersTasksHead

export const dynamic = 'force-dynamic';

export default function DashBoardLayout({children}: {children: React.ReactNode}) {
    return (
        <>
            <header>
                <HeaderUsuario/>
            </header>
            {children}
        </>
    )

}