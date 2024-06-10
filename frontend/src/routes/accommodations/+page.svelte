<script>
    import AccoCard from "./AccoCard.svelte";
</script>

<div class="flex flex-col min-h-screen">
    <header>
        <div id="logo" class="w-[1440px] flex items-center justify-between p-4 py-6">
            <a class="text-2xl font-semibold" href="/hosting">Airdnb</a>
        </div>
    </header>
    <section class=" grow flex">
        <section class="grow-[1]">
            <div class="flex flex-wrap gap-5 content-start">
                <AccoCard />
                <AccoCard />
                <AccoCard />
                <AccoCard />
                <AccoCard />
                <AccoCard />
                <AccoCard />
                <AccoCard />
                <AccoCard />
            </div>
        </section>
        <section class="grow-[1]">
            지도
        </section>
    </section>
</div>