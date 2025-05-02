import "./global.css"
export default function DashBoardLayout({children}: {children: React.ReactNode}) {
    return (
        <>
            <html>
                <body>
                    {children} 
                </body>
            </html>
        </>
    )

}